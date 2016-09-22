package carpentern.handler;

import carpentern.request.HttpRequest;
import carpentern.response.HttpResponse;

public interface Handler {
  public abstract HttpResponse handleRoute(HttpRequest request);
}