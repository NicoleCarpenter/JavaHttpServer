import handler.Handler;
import request.HttpRequest;
import router.Router;

import java.util.HashMap;

public class MockHttpRouter implements Router {
  boolean getRouteCalled = false;
  boolean registerRouteCalled = false;
  boolean registerMethodHandlerCalled = false;

  public Handler getRoute(HttpRequest request) {
    getRouteCalled = true;
    return new MockHandler();
  }

  public void registerRoute(String uriWithMethod, Handler handler) {
    registerRouteCalled = true;
  }

  public void registerMethodHandler(String uriWithMethod, Handler handler) {
    registerMethodHandlerCalled = true;
  }
}
