import com.carpentern.*;

import java.util.HashMap;

public class RootHandlerTest extends junit.framework.TestCase {
  Handler handler;
  Response response;
  String responseBody;

  protected void setUp() {
    responseBody = "This is the root";
    MockHttpFileIO fileIO = new MockHttpFileIO();
    fileIO.stubResponseBody(responseBody);

    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);
    handler = new RootHandler(responseBuilder);
    HttpRequest request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<String, String>(), "");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals(responseBody, response.getBody());
  }
}