import handler.RedirectHandler;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;
import java.io.File;

public class RedirectHandlerTest extends junit.framework.TestCase {
  private Response response;

  protected void setUp() {
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);
    RedirectHandler handler = new RedirectHandler(responseBuilder);

    HttpRequest request = new HttpRequest("GET", "/redirect", "", "HTTP/1.1", new HashMap<String, String>(), "");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");
    testHeaders.put("Location", "http://localhost:5000/");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("302", response.getStatusCode());
    assertEquals("REDIRECT", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", response.getBody());
  }
}