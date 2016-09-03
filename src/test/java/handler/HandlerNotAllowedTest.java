import handler.Handler;
import handler.HandlerNotAllowed;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;

public class HandlerNotAllowedTest extends junit.framework.TestCase {
  private Handler handler;
  private Response response;

  protected void setUp() {
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);
    handler = new HandlerNotAllowed(responseBuilder);
    HttpRequest request = new HttpRequest("mockMethod", "mockUri", "mockParams", "mockHttpVersion", new HashMap<String, String>(), "mockBody");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("405", response.getStatusCode());
    assertEquals("Method not allowed", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", response.bodyToString());
  }
}