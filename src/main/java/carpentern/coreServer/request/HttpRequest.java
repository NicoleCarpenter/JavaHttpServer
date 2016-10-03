package carpentern.coreServer.request;

import java.util.HashMap;

public class HttpRequest {
  private String method;
  private String uri;
  private HashMap<String, String> params;
  private String httpVersion;
  private HashMap<String, String> headerLines;
  private String body;

  public HttpRequest(String method, String uri, HashMap<String, String> params, String httpVersion, HashMap<String, String> headerLines, String body) {
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

  public HashMap<String, String> getParams() {
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

}