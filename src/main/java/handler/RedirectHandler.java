package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;

public class RedirectHandler implements Handler {
  ResponseBuilder responseBuilder;

  public RedirectHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new byte[0];
    responseBuilder.buildRedirectResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}