import handler.Handler;
import router.Routes;
import util.HttpMethods;
import java.util.HashMap;

public class RoutesTest extends junit.framework.TestCase {
  Routes routes;
  HashMap<String, HashMap<String, Handler>> routesList;

  protected void setUp() {
    routes = new Routes();
  }

  private boolean routeExists(String uri, HashMap<String, HashMap<String, Handler>> routesList) {
    return routesList.get(uri) != null;
  }

  public void testAddRouteExists() {
    routes.addRoute("/index", HttpMethods.get, new MockHandler());
    routesList = routes.getRoutes();
    assertTrue(routeExists("/index", routesList));
  }

  public void testAddRouteDoesNotExist() {
    routesList = routes.getRoutes();
    assertFalse(routeExists("/foo", routesList));
  }

  public void testGetRoute() {
    routes.addRoute("/index", HttpMethods.get, new MockHandler());
    routesList = routes.getRoutes();
    assertNotNull(routes.getRoute("/index"));
    assertFalse(routesList.isEmpty());
  }

  public void testGetRouteEmpty() {
    routesList = routes.getRoutes();
    assertTrue(routesList.isEmpty());
  }
}