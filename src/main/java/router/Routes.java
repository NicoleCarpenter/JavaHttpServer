package router;

import handler.Handler;
import java.util.HashMap;

public class Routes {
  private HashMap<String, HashMap<String, Handler>> routes;

  public Routes() {
    HashMap<String, Handler> route = new HashMap<String, Handler>();
    routes = new HashMap<String, HashMap<String, Handler>>();
  }

  public void addRoute(String uri, String method, Handler handler) {
    HashMap<String, Handler> newRoute = new HashMap<String, Handler>();
    if (!routeExists(uri)) {
      newRoute.put(method, handler);
      routes.put(uri, newRoute);
    }
  }

  public HashMap<String, Handler> getRoute(String uri) {
    return routes.get(uri);
  }

  public HashMap<String, HashMap<String, Handler>> getRoutes() {
    return routes;
  }

  private boolean routeExists(String uri) {
    return routes.get(uri) != null;
  }

}