package handler;

import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;

public class ParameterDecoderHandler implements Handler {
  private HttpResponseBuilder responseBuilder;

  public ParameterDecoderHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    byte[] params = new String(request.getParams()).getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(params);
    return responseBuilder.getResponse();
  }

}