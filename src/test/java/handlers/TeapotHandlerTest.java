import com.carpentern.*;

import java.util.HashMap;

public class TeapotHandlerTest extends junit.framework.TestCase {
  private Handler handler;
  private Response response;

  protected void setUp() {
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);
    handler = new TeapotHandler(responseBuilder);
  }

  public void testHandleRouteCoffee() {
    HttpRequest request = new HttpRequest("mockMethod", "/coffee", "", "mockHttpVersion", new HashMap<String, String>(), "mockBody");    
    response = handler.handleRoute(request);
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("418", response.getStatusCode());
    assertEquals("Teapot", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("I'm a teapot", response.getBody());
  }

  public void testHandleRouteTea() {
    HttpRequest request = new HttpRequest("mockMethod", "/tea", "", "mockHttpVersion", new HashMap<String, String>(), "mockBody");    
    response = handler.handleRoute(request);
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", response.getBody());
  }
}