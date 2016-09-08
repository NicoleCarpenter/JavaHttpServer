import handler.Handler;
import handler.MethodNotAllowedHandler;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;

public class MethodNotAllowedHandlerTest extends junit.framework.TestCase {
  private Handler handler;
  private Response response;
  private Formatter formatter;

  protected void setUp() {
    formatter = new Formatter();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    handler = new MethodNotAllowedHandler(responseBuilder);
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
    assertEquals("", formatter.bodyToString(response));
  }
}
