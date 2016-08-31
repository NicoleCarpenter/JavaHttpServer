package com.carpentern;

public class TeapotHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public TeapotHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    String uri = request.getUri();
    if (uri.equals("/coffee")) {
      buildCoffeeResponse();
    } else {
      buildTeaResponse();
    }
    return responseBuilder.getResponse();
  }

  private void buildCoffeeResponse() {
    responseBuilder.setStatusCode("418");
    responseBuilder.setStatusMessage("Teapot");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyMessage("I'm a teapot");
  }

  private void buildTeaResponse() {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
  }

}