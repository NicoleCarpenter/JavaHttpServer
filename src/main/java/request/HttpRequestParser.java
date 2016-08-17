package com.carpentern;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Arrays;
import java.io.IOException;

public class HttpRequestParser implements RequestParser {
  static String CRLF = "\r\n\r\n";
  static String CR = "\r\n";
  static String BLANK = "";
  static String SPACE = " ";

  public HttpRequest parseRequest(SocketConnection socket) throws IOException {
    InputStreamReader inStreamReader = new InputStreamReader(socket.getInputStream());
    BufferedReader bufferedReader = new BufferedReader(inStreamReader);
    
    String rawRequest = readFromBuffer(bufferedReader);
    String[] requestLines = split(rawRequest, CRLF);
    String head = requestLines[0];

    String method = splitRequestStartLine(head)[0];
    String uri = splitRequestStartLine(head)[1];
    String httpVersion = splitRequestStartLine(head)[2];
    HashMap<String, String> headerLines = getHeaderLines(head);
    String body = getBody(requestLines);

    HttpRequest httpRequest = new HttpRequest(method, uri, httpVersion, headerLines, body);
    
    return httpRequest;
  }

  private static String readFromBuffer(BufferedReader bufferedReader) throws IOException {
    StringBuilder rawRequest = new StringBuilder();
    int value;
    while ((value = bufferedReader.read()) != -1) {
      char c = (char)value;
      rawRequest.append(c);
    }
    return rawRequest.toString();
  }

  private String[] split(String data, String separator) {
    data = data.replace("\\", "\\\\");
    return data.split(separator);
  }

  private String[] splitRequestStartLine(String head) {
    String[] headLines = split(head, CR);
    String startLine = headLines[0];
    String[] startLineElements = split(startLine, SPACE);
    return startLineElements;
  }

  private HashMap<String, String> getHeaderLines(String head) {
    String[] headLines = split(head, CR);
    HashMap<String, String> headers = new HashMap<>();
    
    for (int i = 1; i < headLines.length; i++) {
      String[] headerInfo = split(headLines[i], ": ");
      headers.put(headerInfo[0], headerInfo[1]);
    }
    return headers;
  }

  private String getBody(String[] requestLines) {
    if (requestLines.length == 2) {
      return requestLines[1];
    } else {
      return BLANK;
    }
  }

}