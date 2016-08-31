import com.carpentern.*;

import java.util.HashMap;

public class NotFoundHandlerTest extends junit.framework.TestCase {
  private Handler handler;
  private Response response;

  protected void setUp() {
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);
    handler = new NotFoundHandler(responseBuilder);
    HttpRequest request = new HttpRequest("GET", "mockUri", "", "HTTP/1.1", new HashMap<String, String>(), "");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("404", response.getStatusCode());
    assertEquals("Not Found", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", response.getBody());
  }
}