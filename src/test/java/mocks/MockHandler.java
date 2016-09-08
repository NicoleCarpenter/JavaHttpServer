import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import handler.Handler;
import java.util.HashMap;

public class MockHandler implements Handler {
  private boolean handleRouteCalled = false;

  public Response handleRoute(HttpRequest request) {
    handleRouteCalled = true;
    Response response = buildMockResponse();
    return response;
  }

  public boolean handleRouteCalled() {
    return handleRouteCalled;
  }

  private Response buildMockResponse() {
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}