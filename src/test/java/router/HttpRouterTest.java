import handler.Handler;
import request.HttpRequest;
import response.HttpResponseBuilder;
import router.HttpRouter;
import java.util.HashMap;
import java.io.File;

public class HttpRouterTest extends junit.framework.TestCase {
  private HttpRequest request;
  private HttpRouter router;

  protected void setUp() {
    File rootDirectory = new File("/public");
    MockHttpFileSystem fileSystem = new MockHttpFileSystem();
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    router = new HttpRouter(rootDirectory, fileSystem, fileIO, responseBuilder);
  }

  public void testGetRootRoute() {
    request = new HttpRequest("GET", "/", "", "HTTP/1.0", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(true);
  }

  public void testGetFileRoute() {
    assertTrue(true);
  }

  public void testGetDirectoryRoute() {
    assertTrue(true);
  }


}