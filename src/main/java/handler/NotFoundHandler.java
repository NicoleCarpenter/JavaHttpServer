package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;

public class NotFoundHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public NotFoundHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildNotFoundResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }

}