package carpentern.coreServer.router;

import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;

public interface Router {
  Handler getRoute(HttpRequest request);
}