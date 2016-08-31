package com.carpentern;

import java.util.HashMap;
import java.io.File;

public class HttpRequest {
  private String method;
  private String uri;
  private String params;
  private String httpVersion;
  private HashMap<String, String> headerLines;
  private String body;

  public HttpRequest(String method, String uri, String params, String httpVersion, HashMap<String, String> headerLines, String body) {
    this.method = method;
    this.uri = uri;
    this.params = params;
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

  public String getParams() {
    return params;
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

  public String getPathFromRoot(File root) {
    String rootName = root.getName();
    return uri.replace(rootName, "");
  }
}