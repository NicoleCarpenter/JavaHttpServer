import carpentern.handler.Handler;
import carpentern.handler.PatchHandler;
import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;
import carpentern.response.HttpResponseBuilder;
import java.util.HashMap;

public class PatchHandlerTest extends junit.framework.TestCase {
  private Formatter formatter;
  private HttpResponse response;
  private String responseBody;
  private MockHttpFileIO fileIO;
  private HttpResponseBuilder responseBuilder;
  private HashMap<String, String> testHeaders;
  private HashMap<String, String> requestHeaders;

  public void setUp() {
    formatter = new Formatter();
    fileIO = new MockHttpFileIO();
    responseBuilder = new HttpResponseBuilder();
    requestHeaders = new HashMap<String, String>();
    testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");
  }

  public void tearDown() {
    fileIO = null;
    responseBuilder = null;
    requestHeaders = null;
    testHeaders = null;
  }

  private HttpResponse testHandlerResponse(String responseBody) {
    fileIO.stubResponseBody(responseBody);
    Handler handler = new PatchHandler(responseBuilder, fileIO);
    HttpRequest request = new HttpRequest("PATCH", "/file", new HashMap<>(), "HTTP/1.1", requestHeaders, "");

    return handler.handleRoute(request);
  }

  public void testHandleRouteIncludesEtag() {
    requestHeaders.put("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");
    testHeaders.put("Etag", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");
    responseBody = "";

    response = testHandlerResponse(responseBody);

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("204", response.getStatusCode());
    assertEquals("Patched Content", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals(responseBody, formatter.bodyToString(response));
  }

  public void testHandleRouteNoEtag() {
    responseBody = "Some content";
    testHeaders.put("Etag", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");

    fileIO.stubResponseBody(responseBody);
    Handler handler = new PatchHandler(responseBuilder, fileIO);
    HttpRequest request = new HttpRequest("PATCH", "/file", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");

    response = handler.handleRoute(request);

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals(responseBody, formatter.bodyToString(response));
  }

  public void testHandleRouteSideEffects() {
    responseBody = "Some content";
    testHeaders.put("Etag", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");

    fileIO.stubResponseBody(responseBody);
    Handler handler = new PatchHandler(responseBuilder, fileIO);
    HttpRequest request = new HttpRequest("PATCH", "/file", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");

    response = handler.handleRoute(request);
    
    assertTrue(fileIO.getRootDirectoryCalled);
    assertTrue(fileIO.writeToFileCalled);
  }
}
