package carpentern.handler;

import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;
import carpentern.response.ResponseBuilder;

public class DefaultHandler implements Handler {
  ResponseBuilder responseBuilder;

  public DefaultHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new byte[0];
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }

}