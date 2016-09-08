package router;

import io.FileIO;
import io.HttpFileIO;
import file.FileSystem;
import handler.*;
import request.HttpRequest;
import response.HttpResponseBuilder;
import util.RequestLogger;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class HttpRouter implements Router {
  private File rootDirectory;
  private FileSystem fileSystem;
  private FileIO fileIO;
  private HttpResponseBuilder responseBuilder;
  private Map<String, Handler> routes;
  private Map<String, Handler> methodHandlers;

  public HttpRouter(File rootDirectory, FileSystem fileSystem, FileIO fileIO, HttpResponseBuilder responseBuilder) {
    this.rootDirectory = rootDirectory;
    this.fileSystem = fileSystem;
    this.fileIO = fileIO;
    this.responseBuilder = responseBuilder;
    this.routes = new HashMap<>();
    this.methodHandlers = new HashMap<>();
  }

  @Override
  public Handler getRoute(HttpRequest request) {
    String method = request.getMethod();
    String methodWithUri = request.getMethodWithUri();
    String path = findPath(request);
    Handler handler = getHandler(methodWithUri);
    RequestLogger.log(request);

    if (!handlerFound(handler)) {
      handler = getHandlerFromMethod(method);
    }

    if (handlerCanAccessFiles(handler)) {
      if (!fileSystem.exists(path)) {
        handler = new NotFoundHandler(responseBuilder);
        registerRoute(methodWithUri, handler);
      } else {
        registerRoute(methodWithUri, handler);
      }
    }
    return handler;
  }

  public void registerRoute(String methodWithUri, Handler handler) {
    routes.put(methodWithUri, handler);
  }

  public void registerMethodHandler(String method, Handler handler) {
    methodHandlers.put(method, handler);
  }

  private Handler getHandler(String identifier) {
    return routes.get(identifier);
  }

  private boolean handlerFound(Handler handler) {
    return handler != null;
  }

  private boolean handlerCanAccessFiles(Handler handler) {
    return handler.getClass() == FileHandler.class || handler.getClass() == HeadHandler.class;
  }

  private Handler getHandlerFromMethod(String method) {
    Handler handler = methodHandlers.get(method);
    if (!handlerFound(handler)) {
      handler = new MethodNotAllowedHandler(responseBuilder);
    }
    return handler;
  }

  private String findPath(HttpRequest request) {
    String uri = request.getUri();
    File rootDirectory = fileIO.getRootDirectory();
    String rootPath = rootDirectory.getAbsolutePath();
    String requestPath = uri.replace(rootDirectory.getName(), "");
    return rootPath + requestPath;
  }

}
