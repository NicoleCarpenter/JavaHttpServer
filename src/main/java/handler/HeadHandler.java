package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.HttpResponseBuilder;

public class HeadHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public HeadHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}