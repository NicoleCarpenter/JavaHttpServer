package com.carpentern;

import java.util.HashMap;

public class HttpRequestParser implements RequestParser {

  public HttpRequest parseRequest(SocketConnection socket) {
    return new HttpRequest("GET", "/", "HTTP/1.0", new HashMap<String, String>(), "");
  }

}