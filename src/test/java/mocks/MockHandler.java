import request.HttpRequest;
import response.HttpResponse;
import response.HttpResponseBuilder;
import handler.Handler;
import java.util.HashMap;

public class MockHandler implements Handler {
  private boolean handleRouteCalled = false;

  public HttpResponse handleRoute(HttpRequest request) {
    handleRouteCalled = true;
    HttpResponse response = buildMockResponse();
    return response;
  }

  public boolean handleRouteCalled() {
    return handleRouteCalled;
  }

  private HttpResponse buildMockResponse() {
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}