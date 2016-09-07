import handler.Handler;
import request.HttpRequest;
import router.Router;

import java.util.HashMap;

public class MockHttpRouter implements Router {
  private boolean getRouteCalled = false;

  public Handler getRoute(HttpRequest request) {
    getRouteCalled = true;
    return new MockHandler();
  }

  public boolean getRouteCalled() {
    return getRouteCalled;
  }

  public void registerRoute(String uriWithMethod, Handler handler) {

  }

  public void registerMethodHandler(String uriWithMethod, Handler handler) {
    
  }
}