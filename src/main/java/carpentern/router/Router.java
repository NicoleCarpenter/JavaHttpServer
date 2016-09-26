package carpentern.coreServer.router;

import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;

public interface Router {
  public abstract Handler getRoute(HttpRequest request);
}