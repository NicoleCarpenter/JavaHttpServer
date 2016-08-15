package com.carpentern;

import java.util.HashMap;

public class HttpRequest {
  String method;
  String uri;
  String httpVersion;
  HashMap<String, String> headerLines;
  String body;

  public HttpRequest(String method, String uri, String httpVersion, HashMap<String, String> headerLines, String body) {
    this.method = method;
    this.uri = uri;
    this.httpVersion = httpVersion;
    this.headerLines = headerLines;
    this.body = body;
  }

  public String getMethod() {
    return method;
  }

  public String getUri() {
    return uri;
  }

  public String getHttpVersion() {
    return httpVersion;
  }

  public HashMap<String, String> getHeaderLines() {
    return headerLines;
  }

  public String getBody() {
    return body;
  }
}