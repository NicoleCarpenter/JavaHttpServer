package request;

import io.ServerIO;
import socket.SocketConnection;
import java.net.URLDecoder;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

public class HttpRequestParser implements RequestParser {
  private ServerIO serverIO;
  private static String CRLF = "\r\n\r\n";
  private static String CR = "\r\n";
  private static String NEWLINE = "\n";
  private static String BLANK = "";
  private static String SPACE = " ";

  public HttpRequestParser(ServerIO serverIO) {
    this.serverIO = serverIO;
  }

  @Override
  public HttpRequest parseRequest(SocketConnection socket) throws IOException {
    String rawRequest = serverIO.readRequest(socket.getInputStream());
    String[] requestLines = split(rawRequest, CRLF);
    String head = requestLines[0];

    String method = getMethod(head);
    String uri = getUri(head);
    String params = parseParams(head);
    String httpVersion = getHttpVersion(head);
    HashMap<String, String> headerLines = getHeaderLines(head);
    String body = getBody(requestLines);

    HttpRequest httpRequest = new HttpRequest(method, uri, params, httpVersion, headerLines, body);

    return httpRequest;
  }

  private String[] split(String data, String separator) {
    data = data.replace("\\", "\\\\");
    return data.split(separator);
  }

  private String getMethod(String head) {
    return splitRequestStartLine(head)[0];
  }

  private String getFullUri(String head) {
    return splitRequestStartLine(head)[1];
  }

  private String getUri(String head) {
    String fullUri = getFullUri(head);
    return fullUri.split("\\?")[0];
  }

  private String getHttpVersion(String head) {
    return splitRequestStartLine(head)[2];
  }

  private String getRequestStartLine(String head) {
    String[] headLines = split(head, CR);
    return headLines[0];
  }

  private String[] splitRequestStartLine(String head) {
    String startLine = getRequestStartLine(head);
    String[] startLineElements = split(startLine, SPACE);
    return startLineElements;
  }

  private HashMap<String, String> getHeaderLines(String head) {
    String[] headLines = split(head, NEWLINE);
    HashMap<String, String> headers = new HashMap<>();
    int length = headLines.length;

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

  private String parseParams(String head) {
    String startLine = getRequestStartLine(head);
    String[] params = getParams(startLine).split("&");
    return buildParams(params);
  }

  private String getParams(String head) {
    String params = "";
    String wholeUri = getFullUri(head);
    String[] uri = wholeUri.split("\\?");
    if (uri.length > 1) {
      params = uri[1];
    }
    return params;
  }

  private String buildParams(String[] params) {
    String decodedParams = "";
    try {
      for (String param : params) {
        if (param.contains("=")) {
          decodedParams += formatParam(param);
        } else {
          decodedParams += decode(param) + "\n";
        }
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return decodedParams;
  }

  private String formatParam(String param) throws UnsupportedEncodingException {
    String[] parts = param.split("=");
    return parts[0] + " = " + decode(parts[1]) + "\n";
  }

  private String decode(String param) throws UnsupportedEncodingException {
    return URLDecoder.decode(param, "UTF-8");
  }

}
