package com.carpentern;

import java.util.HashMap;

public class HttpResponse {
  String httpVersion;
  String responseCode;
  String responseMessage;
  HashMap<String, String> headerLines;
  String body;
  static String CRLF = "\r\n\r\n";
  static String CR = "\r\n";

  public HttpResponse(String httpVersion, String responseCode, String responseMessage, HashMap<String, String> headerLines, String body) {
    this.httpVersion = httpVersion;
    this.responseCode = responseCode;
    this.responseMessage = responseMessage;
    this.headerLines = headerLines;
    this.body = body;
  }
  
  public String getHttpVersion() {
    return httpVersion;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public HashMap<String, String> getHeaderLines() {
    return headerLines;
  }

  public String getBody() {
    return body;
  }

  public byte[] formatToBytes() {
    return (httpVersion 
            + responseCode 
            + responseMessage
            + CR
            + headersToString()
            + CRLF
            + body
            + CRLF).getBytes();
  }

  public String headersToString() {
    StringBuilder builder = new StringBuilder();
    headerLines.forEach((key, value)-> builder.append(key + ": " + value + "\r\n"));
    return builder.toString();
  }
}