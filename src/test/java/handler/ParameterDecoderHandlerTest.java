import handler.Handler;
import handler.ParameterDecoderHandler;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;

public class ParameterDecoderHandlerTest extends junit.framework.TestCase {
  private Handler handler;
  private Response response;

  protected void setUp() {
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    handler = new ParameterDecoderHandler(responseBuilder);
    HttpRequest request = new HttpRequest("mockMethod", "mockUri", "variable_2 = stuff", "mockHttpVersion", new HashMap<String, String>(), "mockBody");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("variable_2 = stuff", response.bodyToString());
  }
}