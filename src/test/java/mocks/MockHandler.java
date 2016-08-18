import com.carpentern.*;

import java.util.HashMap;

public class MockHandler implements Handler {
  boolean handleRouteCalled = false;

  public Response handleRoute(HttpRequest request) {
    handleRouteCalled = true;
    return new MockHttpResponse();
  }

  public boolean handleRouteCalled() {
    return handleRouteCalled;
  }
}