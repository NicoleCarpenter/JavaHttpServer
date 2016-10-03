import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.handler.Handler;

class MockHandler implements Handler {
  private boolean handleRouteCalled = false;

  public HttpResponse handleRoute(HttpRequest request) {
    handleRouteCalled = true;
    return new HttpResponse();
  }

  public boolean handleRouteCalled() {
    return handleRouteCalled;
  }

}