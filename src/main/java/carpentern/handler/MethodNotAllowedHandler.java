package carpentern.handler;

import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;
import carpentern.response.ResponseBuilder;
import java.util.HashMap;

public class MethodNotAllowedHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public MethodNotAllowedHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new byte[0];
    responseBuilder.buildMethodNotAllowedResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}