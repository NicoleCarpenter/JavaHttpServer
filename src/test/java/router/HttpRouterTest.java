import handler.*;
import request.HttpRequest;
import response.HttpResponseBuilder;
import router.HttpRouter;
import util.FileTypeMatcher;
import util.SetUp;
import java.util.HashMap;
import java.io.File;

public class HttpRouterTest extends junit.framework.TestCase {
  private HttpRequest request;
  private HttpRouter router;
  private MockHttpFileSystem fileSystem;

  protected void setUp() {
    FileTypeMatcher typeMatcher = new FileTypeMatcher();
    File rootDirectory = new File("/public");
    fileSystem = new MockHttpFileSystem();
    MockHttpFileIO fileIO = new MockHttpFileIO();
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    router = new HttpRouter(rootDirectory, fileSystem, fileIO, responseBuilder);

    SetUp setUp = new SetUp();
    setUp.registerRoutes(router, responseBuilder, fileSystem, fileIO);
    setUp.registerMethodHandlers(router, responseBuilder, fileSystem, fileIO, typeMatcher);
  }

  protected void tearDown() {
    fileSystem = null;
  }

  public void testGetRootRoute() {
    fileSystem.stubExists(true);
    request = new HttpRequest("GET", "/", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof FileHandler);
  }

  public void testGetParametersRoute() {
    request = new HttpRequest("GET", "/parameters", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof ParameterDecoderHandler);
  }

  public void testGetCoffeeRoute() {
    request = new HttpRequest("GET", "/coffee", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof TeapotHandler);
  }

  public void testGetTeaRoute() {
    request = new HttpRequest("GET", "/tea", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof TeapotHandler);
  }

  public void testGetRedirectRoute() {
    request = new HttpRequest("GET", "/redirect", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof RedirectHandler);
  }

  public void testGetLogsRoute() {
    request = new HttpRequest("GET", "/logs", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof BasicAuthHandler);
  }

  public void testGetFormRoute() {
    request = new HttpRequest("GET", "/form", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof FormHandler);
  }

  public void testGetFormRoutePost() {
    request = new HttpRequest("POST", "/form", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof FormHandler);
  }

  public void testGetFormRoutePut() {
    request = new HttpRequest("PUT", "/form", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof FormHandler);
  }

  public void testGetFormRouteDelete() {
    request = new HttpRequest("DELETE", "/form", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof FormHandler);
  }

  public void testGetRoutePatch() {
    request = new HttpRequest("PATCH", "/file", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof PatchHandler);
  }

  public void testGetRouteOptions() {
    request = new HttpRequest("OPTIONS", "/file", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof MethodOptionsHandler);
  }

  public void testGetRouteHead() {
    fileSystem.stubExists(true);
    request = new HttpRequest("HEAD", "/file", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof HeadHandler);
  }

  public void testGetRouteHeadNotFound() {
    fileSystem.stubExists(false);
    request = new HttpRequest("HEAD", "/nonExistantFile", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof NotFoundHandler);
  }

  public void testGetRouteFile() {
    fileSystem.stubExists(true);
    request = new HttpRequest("GET", "/file", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof FileHandler);
  }

  public void testGetRouteFileNotFound() {
    fileSystem.stubExists(false);
    request = new HttpRequest("GET", "/nonExistantFile", "", "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof NotFoundHandler);
  }

}
