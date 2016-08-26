package com.carpentern;

public class RootHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public RootHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyMessage("This is the root");
    return responseBuilder.getResponse();
  } 
}