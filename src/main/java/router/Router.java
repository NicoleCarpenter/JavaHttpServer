package com.carpentern;

public interface Router {
  public abstract Handler getRoute(HttpRequest request);
}