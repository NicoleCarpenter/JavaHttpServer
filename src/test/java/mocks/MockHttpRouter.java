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
}
