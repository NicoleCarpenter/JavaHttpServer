package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;

public class TeapotHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public TeapotHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String uri = request.getUri();
    if (uri.equals("/coffee")) {
      buildCoffeeResponse();
    } else {
      buildTeaResponse();
    }
    return responseBuilder.getResponse();
  }

  private void buildCoffeeResponse() {
    byte[] teapotMessage = new String("I'm a teapot").getBytes();
    responseBuilder.buildCoffeeResponse();
    responseBuilder.setBody(teapotMessage);
  }

  private void buildTeaResponse() {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
  }

}