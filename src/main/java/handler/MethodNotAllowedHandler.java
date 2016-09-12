package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;
import java.util.HashMap;

public class MethodNotAllowedHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public MethodNotAllowedHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildMethodNotAllowedResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}