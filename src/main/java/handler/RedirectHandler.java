package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;

public class RedirectHandler implements Handler {
  HttpResponseBuilder responseBuilder;

  public RedirectHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildRedirectResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}