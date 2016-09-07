package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;

public class HeadHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public HeadHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}