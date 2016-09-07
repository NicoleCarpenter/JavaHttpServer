package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;

public class NotFoundHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public NotFoundHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildNotFoundResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }

}