import com.carpentern.*;

import java.util.HashMap;

public class HandlerNotAllowedTest extends junit.framework.TestCase {
  Handler handler;
  Response response;

  protected void setUp() {
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);
    handler = new HandlerNotAllowed(responseBuilder);
    HttpRequest request = new HttpRequest("mockMethod", "mockUri", "mockHttpVersion", new HashMap<String, String>(), "mockBody");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("405", response.getStatusCode());
    assertEquals("Method not allowed", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("Method not allowed", response.getBody());
  }
}