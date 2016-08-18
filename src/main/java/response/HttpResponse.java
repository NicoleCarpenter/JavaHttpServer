package com.carpentern;

import java.util.HashMap;

public class HttpResponse implements Response {
  static String CRLF = "\r\n\r\n";
  static String CR = "\r\n";
  String httpVersion = "HTTP/1.1";
  String statusCode;
  String statusMessage;
  HashMap<String, String> headerLines = new HashMap<String, String>();
  byte[] body;
  
  public String getHttpVersion() {
    return httpVersion;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public HashMap<String, String> getHeaderLines() {
    return headerLines;
  }

  public String getBody() {
    return new String(body);
  }

  public void setHeader(String key, String value) {
    headerLines.put(key, value);
  }

  public String formatToString() {
    byte[] ba = formatToBytes();
    return new String(ba);
  }

  public String headersToString() {
    StringBuilder builder = new StringBuilder();
    headerLines.forEach((key, value)-> builder.append(key + ": " + value + CR));
    return builder.toString();
  }

  public String bodyToString() {
    return new String(body);
  }

  public byte[] formatToBytes() {
    return (httpVersion + " "
            + statusCode + " "
            + statusMessage
            + CR
            + headersToString()
            + CR
            + bodyToString()
            + CRLF).getBytes();
  }

}