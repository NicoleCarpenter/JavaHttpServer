package carpentern.coreServer.httpServer.handler;

import carpentern.coreServer.httpServer.request.HttpRequest;
import carpentern.coreServer.httpServer.response.HttpResponse;
import carpentern.coreServer.httpServer.response.ResponseBuilder;
import java.util.ArrayList;

public class MethodOptionsHandler extends DefaultHandler implements Handler {
  private ArrayList<String> methodOptions;

  public MethodOptionsHandler(ResponseBuilder responseBuilder, ArrayList<String> methodOptions) {
    super(responseBuilder);
    this.methodOptions = methodOptions;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new byte[0];
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
    getAllowedMethodsHeader(request);
    return responseBuilder.getResponse();
  }

  private void getAllowedMethodsHeader(HttpRequest request) {
    StringBuilder builder = new StringBuilder();
    for (String item : methodOptions) {
      if (builder.length() != 0) {
        builder.append(",");
      }
      builder.append(item);
    }
    responseBuilder.setHeader("Allow", builder.toString());
  }
}