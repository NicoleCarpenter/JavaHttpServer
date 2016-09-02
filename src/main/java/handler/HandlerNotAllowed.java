package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.util.HashMap;

public class HandlerNotAllowed implements Handler {
  private HttpResponseBuilder responseBuilder;

  public HandlerNotAllowed(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    responseBuilder.setStatusCode("405");
    responseBuilder.setStatusMessage("Method not allowed");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyEmpty();
    return responseBuilder.getResponse();
  }
}