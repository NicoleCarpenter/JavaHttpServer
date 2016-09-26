package carpentern.coreServer.httpServer.handler;

import carpentern.coreServer.httpServer.request.HttpRequest;
import carpentern.coreServer.httpServer.response.HttpResponse;
import carpentern.coreServer.httpServer.response.ResponseBuilder;

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