package router;

import io.FileIO;
import file.FileSystem;
import handler.*;
import request.HttpRequest;
import response.ResponseBuilder;
import util.Config;
import util.FileTypeMatcher;
import util.RequestLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class HttpRouter implements Router {
  private File rootDirectory;
  private FileSystem fileSystem;
  private FileIO fileIO;
  private ResponseBuilder responseBuilder;
  private FileTypeMatcher typeMatcher;
  private HashMap<String, Handler> routes;
  private HashMap<String, Handler> methodHandlers;

  public HttpRouter(File rootDirectory, FileSystem fileSystem, FileIO fileIO, ResponseBuilder responseBuilder, FileTypeMatcher typeMatcher) {
    this.rootDirectory = rootDirectory;
    this.fileSystem = fileSystem;
    this.fileIO = fileIO;
    this.responseBuilder = responseBuilder;
    this.typeMatcher = typeMatcher;
    this.methodHandlers = new HashMap<>();
  }

  @Override
  public Handler getRoute(HttpRequest request) {
    RequestLogger.log(request);
    Handler handler = new FileHandler(responseBuilder, fileSystem, fileIO, typeMatcher);
    String method = request.getMethod();
    String uri = request.getUri();
    Routes configRoutes = Config.routes;
    HashMap<String, Handler> route = configRoutes.getRouteForUri(uri);

    if (route != null) {
      ArrayList<String> methodOptions = configRoutes.getMethodOptions(uri);
      if (method.equals("OPTIONS")) {
        handler = new MethodOptionsHandler(responseBuilder, methodOptions);
      } else if (methodOptions.contains(method)) {
        handler = route.get(method);
      } else {
        handler = new MethodNotAllowedHandler(responseBuilder);
      }
    } else {
      handler = new NotFoundHandler(responseBuilder);
    }
    return handler;
  }

}