import handler.MethodOptionsHandler;
import request.HttpRequest;
import response.HttpResponse;
import response.HttpResponseBuilder;
import java.util.HashMap;
import java.io.File;

public class MethodOptionsHandlerTest extends junit.framework.TestCase {
  private HttpResponse response;
  private Formatter formatter;
  private MethodOptionsHandler handler;
  private HashMap<String, String> testHeaders;

  protected void setUp() {
    formatter = new Formatter();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    handler = new MethodOptionsHandler(responseBuilder);
    testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");
  }

  private HttpResponse testHandlerResponse(String uri, String allowedMethods) {
    HttpRequest request = new HttpRequest("HEAD", uri, new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "");
    testHeaders.put("Allow", allowedMethods);
    return handler.handleRoute(request);
  }

  public void testHandleRouteMethod() {
    String uri = "/method_options";
    String allowedMethods = "GET,HEAD,POST,OPTIONS,PATCH,PUT";

    HttpResponse response = testHandlerResponse(uri, allowedMethods);

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", formatter.bodyToString(response));
  }

  public void testHandleRouteMethod2() {
    String uri = "/method_options2";
    String allowedMethods = "GET,OPTIONS";

    HttpResponse response = testHandlerResponse(uri, allowedMethods);

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", formatter.bodyToString(response));
  }
}