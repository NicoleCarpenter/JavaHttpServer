package carpentern.handler;

import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;
import carpentern.response.ResponseBuilder;

public class NotFoundHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public NotFoundHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new byte[0];
    responseBuilder.buildNotFoundResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }

}