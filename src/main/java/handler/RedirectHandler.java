package com.carpentern;

public class RedirectHandler implements Handler {
  HttpResponseBuilder responseBuilder;

  public RedirectHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("302");
    responseBuilder.setStatusMessage("REDIRECT");
    responseBuilder.setHeader("Location", "http://localhost:5000/");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
    return responseBuilder.getResponse();
  }
}