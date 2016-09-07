import handler.HeadHandler;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;
import java.io.File;

public class HeadHandlerTest extends junit.framework.TestCase {
  private Response response;

  protected void setUp() {
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    HeadHandler handler = new HeadHandler(responseBuilder);

    HttpRequest request = new HttpRequest("HEAD", "/", "", "HTTP/1.1", new HashMap<String, String>(), "");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", response.bodyToString());
  }
}