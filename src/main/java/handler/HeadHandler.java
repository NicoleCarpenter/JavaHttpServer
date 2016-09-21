package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;

public class HeadHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public HeadHandler(ResponseBuilder responseBuilder) {
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