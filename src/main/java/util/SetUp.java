package util;

import handler.*;
import router.Router;
import response.HttpResponseBuilder;
import file.FileSystem;
import io.FileIO;

public class SetUp {

  public void setUpRouter(Router router, HttpResponseBuilder responseBuilder, FileSystem fileSystem, FileIO fileIO) {
    registerRoutes(router, responseBuilder, fileSystem, fileIO);
    registerMethodHandlers(router, responseBuilder, fileSystem, fileIO);
  }

  public void registerRoutes(Router router, HttpResponseBuilder responseBuilder, FileSystem fileSystem, FileIO fileIO) {
    router.registerRoute("GET /parameters", new ParameterDecoderHandler(responseBuilder));
    router.registerRoute("GET /coffee", new TeapotHandler(responseBuilder));
    router.registerRoute("GET /tea", new TeapotHandler(responseBuilder));
    router.registerRoute("GET /redirect", new RedirectHandler(responseBuilder));
    router.registerRoute("GET /logs", new BasicAuthHandler(responseBuilder));
    router.registerRoute("GET /form", new FormHandler(responseBuilder, fileSystem, fileIO));
    router.registerRoute("POST /form", new FormHandler(responseBuilder, fileSystem, fileIO));
    router.registerRoute("PUT /form", new FormHandler(responseBuilder, fileSystem, fileIO));
    router.registerRoute("DELETE /form", new FormHandler(responseBuilder, fileSystem, fileIO));
  }

  public void registerMethodHandlers(Router router, HttpResponseBuilder responseBuilder, FileSystem fileSystem, FileIO fileIO) {
    router.registerMethodHandler("GET", new FileHandler(responseBuilder, fileSystem, fileIO));
    router.registerMethodHandler("PATCH", new PatchHandler(responseBuilder, fileIO));
    router.registerMethodHandler("HEAD", new HeadHandler(responseBuilder));
    router.registerMethodHandler("OPTIONS", new MethodOptionsHandler(responseBuilder));
  }

}
