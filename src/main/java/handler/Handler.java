package com.carpentern;

public interface Handler {
  public abstract HttpResponse handleRoute(HttpRequest request);
}