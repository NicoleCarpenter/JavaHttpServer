package handler;

import request.HttpRequest;
import response.HttpResponse;

public interface Handler {
  public abstract HttpResponse handleRoute(HttpRequest request);
}