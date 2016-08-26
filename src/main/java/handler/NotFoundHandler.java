package com.carpentern;

public class NotFoundHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public NotFoundHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("404");
    responseBuilder.setStatusMessage("Not Found");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
    return responseBuilder.getResponse();
  }

}