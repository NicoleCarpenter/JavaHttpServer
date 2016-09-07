package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;

public class MethodNotAllowedHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public MethodNotAllowedHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildMethodNotAllowedResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}