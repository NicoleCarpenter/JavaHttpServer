package handler;

import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;

public class ParameterDecoderHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public ParameterDecoderHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] params = new String(request.getParams()).getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(params);
    return responseBuilder.getResponse();
  }

}