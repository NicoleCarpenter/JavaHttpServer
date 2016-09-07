package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;

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