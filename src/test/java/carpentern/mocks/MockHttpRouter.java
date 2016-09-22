import carpentern.handler.Handler;
import carpentern.request.HttpRequest;
import carpentern.router.Router;

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
