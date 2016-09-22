package carpentern.router;

import carpentern.handler.Handler;
import carpentern.request.HttpRequest;

public interface Router {
  public abstract Handler getRoute(HttpRequest request);
}