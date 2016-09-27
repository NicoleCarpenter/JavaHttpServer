package carpentern.coreServer.handler;

import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;

public interface Handler {
  public abstract HttpResponse handleRoute(HttpRequest request);
}