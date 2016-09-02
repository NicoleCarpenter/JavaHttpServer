package handler;

import request.HttpRequest;
import response.Response;

public interface Handler {
  public abstract Response handleRoute(HttpRequest request);
}