import carpentern.handler.DefaultHandler;
import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;
import carpentern.response.HttpResponseBuilder;
import java.util.HashMap;

public class DefaultHandlerTest extends junit.framework.TestCase {
  private HttpResponse response;
  private Formatter formatter;

  protected void setUp() {
    formatter = new Formatter();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    DefaultHandler handler = new DefaultHandler(responseBuilder);

    HttpRequest request = new HttpRequest("GET", "/", new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "");
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", formatter.bodyToString(response));
  }
}
