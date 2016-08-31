package com.carpentern;

import java.io.File;

public class HttpRouter implements Router {
  private File rootDirectory;
  private FileSystem fileSystem;
  private HttpResponseBuilder responseBuilder;

  public HttpRouter(File rootDirectory, FileSystem fileSystem, HttpResponseBuilder responseBuilder) {
    this.rootDirectory = rootDirectory;
    this.fileSystem = fileSystem;
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Handler getRoute(HttpRequest request) {
    String uri = request.getUri();
    String method = request.getMethod();
    String path = rootDirectory.getAbsolutePath() + request.getPathFromRoot(rootDirectory);

    if (method.equals("GET")) {
      if (fileSystem.exists(path)) {
        return new FileHandler(responseBuilder, path, uri, fileSystem);
      } else if (uri.equals("/parameters")) {
        return new ParameterDecoderHandler(responseBuilder);
      } else if (uri.equals("/coffee")) {
        return new TeapotHandler(responseBuilder);
      } else if (uri.equals("/tea")) {
        return new TeapotHandler(responseBuilder);
      } else {
        return new NotFoundHandler(responseBuilder);
      }
    } else if (method.equals("HEAD")) {
      if (fileSystem.exists(path)) {
        return new HeadHandler(responseBuilder);
      } else {
        return new NotFoundHandler(responseBuilder);
      }
    } else if (method.equals("OPTIONS")) {
      return new MethodOptionsHandler(responseBuilder);
    } else {
      return new HandlerNotAllowed(responseBuilder);
    }
  }
}