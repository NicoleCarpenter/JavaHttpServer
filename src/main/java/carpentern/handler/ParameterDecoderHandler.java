package carpentern.coreServer.handler;

import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.response.ResponseBuilder;
import java.util.HashMap;

public class ParameterDecoderHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public ParameterDecoderHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String params = formatParamsToString(request.getParams());
    byte[] byteParams = params.getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(byteParams);
    return responseBuilder.getResponse();
  }

  private String formatParamsToString(HashMap<String, String> params) {
    StringBuilder builder = new StringBuilder();
    String separator = "";
    for (String key : params.keySet()) {
      String value = params.get(key);
      if (value != null) {
        separator = " = ";
      }
      builder.append(key + separator + value + "\n");
    }
    return builder.toString();
  }

}