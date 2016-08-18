package com.carpentern;

public class HttpRouter implements Router {
  HttpResponseBuilder responseBuilder;

  public HttpRouter(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  public Handler getRoute(HttpRequest request) {
    String method = request.getMethod();
    String uri = request.getUri();
    if (method.equals("GET")) {
      if (uri.equals("/")) {
        return new RootHandler(responseBuilder);
      } else if (uri.equals("/foobar")) {
        return new NotFoundHandler(responseBuilder);
      } else {
        return new FileHandler(responseBuilder, new HttpFileSystem(uri));
      }
    } else {
      return new HandlerNotAllowed(responseBuilder);
    }
  }
}