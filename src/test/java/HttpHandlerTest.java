import com.carpentern.*;

import java.util.HashMap;

public class HttpHandlerTest extends junit.framework.TestCase {
  Handler handler;
  HttpResponse response;

  protected void setUp() {
    handler = new HttpHandler();
    HttpRequest request = new HttpRequest("mockMethod", "mockUri", "mockHttpVersion", new HashMap<String, String>(), "mockBody");    
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    assertEquals("HTTP/1.0", response.getHttpVersion());
    assertEquals("200", response.getResponseCode());
    assertEquals("OK", response.getResponseMessage());
    assertEquals(new HashMap<>(), response.getHeaderLines());
    assertEquals("Hello World", response.getBody());
  }
}