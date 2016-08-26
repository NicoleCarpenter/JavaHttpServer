import com.carpentern.*;

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
}