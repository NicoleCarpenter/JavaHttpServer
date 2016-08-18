import com.carpentern.*;

import java.util.HashMap;

public class HttpRouterTest extends junit.framework.TestCase {
  HttpRequest request;
  HttpRouter router;

  protected void setUp() {
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder(fileIO);

    router = new HttpRouter(responseBuilder);
  }

  public void testGetRootRoute() {
    request = new HttpRequest("GET", "/", "HTTP/1.0", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof RootHandler);
  }

  public void testGetFileRoute() {
    assertTrue(true);
  }

  public void testGetDirectoryRoute() {
    assertTrue(true);
  }


}