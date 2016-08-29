package com.carpentern;

public class HttpResponseBuilder {
  HttpResponse response;
  FileIO fileIO;

  public HttpResponseBuilder(FileIO fileIO) {
    response = new HttpResponse();
    this.fileIO = fileIO;
  }

  public void setStatusCode(String code) {
    response.setStatusCode(code);
  }

  public void setStatusMessage(String message) {
    response.setStatusMessage(message);
  }

  public void setDefaultHeaders() {
    response.setHeader("Server", "Nicole's HTTP server");
  }

  public void setHeader(String key, String value) {
    response.setHeader(key, value);
  }

  public void setBody(String file) {
    response.setBody(fileIO.getFileContents(file));
  }

  public void setBodyEmpty() {
    response.setBody(new String("").getBytes());
  }

  public void setBodyMessage(String message) {
    response.setBody(new String(message).getBytes());
  }

  public HttpResponse getResponse() {
    return response;
  }

  public String getContentLength() {
    String body = response.getBody();
    return Integer.toString(body.length());
  }

}