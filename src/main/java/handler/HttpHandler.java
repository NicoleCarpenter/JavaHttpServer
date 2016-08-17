package com.carpentern;

import java.util.HashMap;

public class HttpHandler implements Handler {

  public HttpResponse handleRoute(HttpRequest request) {
    return new HttpResponse("HTTP/1.0", "200", "OK", new HashMap<String, String>(), "Hello World");
  }
}