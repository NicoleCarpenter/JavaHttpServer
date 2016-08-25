package com.carpentern;

public class NotFoundHandler implements Handler {
  HttpResponseBuilder responseBuilder;

  public NotFoundHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("404");
    responseBuilder.setStatusMessage("Not Found");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
    return responseBuilder.getResponse();
  }

}