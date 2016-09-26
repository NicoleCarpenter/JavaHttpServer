package carpentern.coreServer.httpServer.router;

import carpentern.coreServer.httpServer.handler.Handler;
import carpentern.coreServer.httpServer.request.HttpRequest;

public interface Router {
  public abstract Handler getRoute(HttpRequest request);
}