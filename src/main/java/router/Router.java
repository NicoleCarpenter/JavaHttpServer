package router;

import handler.Handler;
import request.HttpRequest;

public interface Router {
  public abstract Handler getRoute(HttpRequest request);
  public abstract void registerRoute(String uriWithMethod, Handler handler);
  public abstract void registerMethodHandler(String method, Handler handler);
}