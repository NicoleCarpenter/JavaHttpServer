import carpentern.handler.Handler;
import carpentern.handler.NotFoundHandler;
import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;
import carpentern.response.HttpResponseBuilder;
import java.util.HashMap;

public class NotFoundHandlerTest extends junit.framework.TestCase {
  private Handler handler;
  private HttpResponse response;
  private Formatter formatter;

  protected void setUp() {
    formatter = new Formatter();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    handler = new NotFoundHandler(responseBuilder);
    HttpRequest request = new HttpRequest("GET", "mockUri", new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "");
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("404", response.getStatusCode());
    assertEquals("Not Found", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", formatter.bodyToString(response));
  }
}
