package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;

public class MethodOptionsHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public MethodOptionsHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
    if (request.getUri().equals("/method_options")) {
      responseBuilder.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PATCH,PUT");
    } else {
      responseBuilder.setHeader("Allow", "GET,OPTIONS");
    }
    return responseBuilder.getResponse();
  }
}