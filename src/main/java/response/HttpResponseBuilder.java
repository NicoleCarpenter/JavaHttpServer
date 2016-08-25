package com.carpentern;

public class HttpResponseBuilder {
  HttpResponse response;
  FileIO fileIO;

  public HttpResponseBuilder(FileIO fileIO) {
    response = new HttpResponse();
    this.fileIO = fileIO;
  }

  public void setStatusCode(String code) {
    response.statusCode = code;
  }

  public void setStatusMessage(String message) {
    response.statusMessage = message;
  }

  public void setDefaultHeaders() {
    response.setHeader("Server", "Nicole's HTTP server");
  }

  public void setBody(String file) {
    response.body = fileIO.getFileContents(file);
  }

  public void setBodyEmpty() {
    response.body = new String("").getBytes();
  }

  public void setBodyMessage(String message) {
    response.body = new String(message).getBytes();
  }

  public HttpResponse getResponse() {
    return response;
  }

  private String getContentLength() {
    String body = response.getBody();
    return Integer.toString(body.length());
  }

}