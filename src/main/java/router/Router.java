package router;

import handler.Handler;
import request.HttpRequest;

public interface Router {
  public abstract Handler getRoute(HttpRequest request);
}