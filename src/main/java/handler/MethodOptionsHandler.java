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
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    getAllowedMethodsHeader(request);
    return responseBuilder.getResponse();
  }

  private void getAllowedMethodsHeader(HttpRequest request) {
    if (request.getUri().equals("/method_options")) {
      responseBuilder.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PATCH,PUT");
    } else {
      responseBuilder.setHeader("Allow", "GET,OPTIONS");
    }
  }
}