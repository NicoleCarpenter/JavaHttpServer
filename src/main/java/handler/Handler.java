package com.carpentern;

public interface Handler {
  public abstract Response handleRoute(HttpRequest request);
}