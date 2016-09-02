import request.HttpRequest;
import response.Response;
import handler.Handler;
import java.util.HashMap;

public class MockHandler implements Handler {
  private boolean handleRouteCalled = false;

  public Response handleRoute(HttpRequest request) {
    handleRouteCalled = true;
    return new MockHttpResponse();
  }

  public boolean handleRouteCalled() {
    return handleRouteCalled;
  }
}