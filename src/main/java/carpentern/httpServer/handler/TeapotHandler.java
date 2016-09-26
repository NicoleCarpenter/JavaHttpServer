package carpentern.coreServer.httpServer.handler;

import carpentern.coreServer.httpServer.request.HttpRequest;
import carpentern.coreServer.httpServer.response.HttpResponse;
import carpentern.coreServer.httpServer.response.ResponseBuilder;

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
    byte[] emptyBody = new byte[0];
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
  }

}