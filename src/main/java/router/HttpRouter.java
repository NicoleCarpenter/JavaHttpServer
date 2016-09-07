package router;

import io.FileIO;
import io.HttpFileIO;
import file.FileSystem;
import handler.*;
import request.HttpRequest;
import response.HttpResponseBuilder;
import util.RequestLogger;
import java.io.File;

public class HttpRouter implements Router {
  private File rootDirectory;
  private FileSystem fileSystem;
  private FileIO fileIO;
  private HttpResponseBuilder responseBuilder;

  public HttpRouter(File rootDirectory, FileSystem fileSystem, FileIO fileIO, HttpResponseBuilder responseBuilder) {
    this.rootDirectory = rootDirectory;
    this.fileSystem = fileSystem;
    this.fileIO = fileIO;
    this.responseBuilder = responseBuilder;
  }

  @Override
  public Handler getRoute(HttpRequest request) {
    String uri = request.getUri();
    String method = request.getMethod();
    String path = findPath(request);

    RequestLogger.log(request);

    if (uri.equals("/form")) {
      return new FormHandler(responseBuilder, fileIO);
    } else if (method.equals("GET")) {
      if (fileSystem.exists(path)) {
        return new FileHandler(responseBuilder, fileSystem, fileIO);
      } else if (uri.equals("/parameters")) {
        return new ParameterDecoderHandler(responseBuilder);
      } else if (uri.equals("/coffee")) {
        return new TeapotHandler(responseBuilder);
      } else if (uri.equals("/tea")) {
        return new TeapotHandler(responseBuilder);
      } else if (uri.equals("/redirect")) {
        return new RedirectHandler(responseBuilder);
      } else if (uri.equals("/logs")) {
        return new BasicAuthHandler(responseBuilder);
      } else {
        return new NotFoundHandler(responseBuilder);
      }
    } else if (method.equals("PATCH")) {
      return new PatchHandler(responseBuilder, fileIO);
    } else if (method.equals("HEAD")) {
      if (fileSystem.exists(path)) {
        return new HeadHandler(responseBuilder);
      } else {
        return new NotFoundHandler(responseBuilder);
      }
    } else if (method.equals("OPTIONS")) {
      return new MethodOptionsHandler(responseBuilder);
    } else {
      return new MethodNotAllowedHandler(responseBuilder);
    }
  }

  private String findPath(HttpRequest request) {
    String uri = request.getUri();  
    File rootDirectory = fileIO.getRootDirectory();
    String rootPath = rootDirectory.getAbsolutePath();
    String requestPath = uri.replace(rootDirectory.getName(), "");
    return rootPath + requestPath;
  }
}