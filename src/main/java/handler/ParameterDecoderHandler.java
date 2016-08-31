package com.carpentern;

public class ParameterDecoderHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public ParameterDecoderHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyMessage(request.getParams());
    return responseBuilder.getResponse();
  }

}