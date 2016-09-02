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
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
    return responseBuilder.getResponse();
  }
}