package carpentern.request;

import carpentern.io.ServerOutput;
import carpentern.parser.HttpParamParser;
import carpentern.socket.SocketConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class HttpRequestParser implements RequestParser {
  private ServerOutput serverIO;
  private HttpParamParser paramParser;
  private static String CRLF = "\r\n\r\n";
  private static String CR = "\r\n";
  private static String NEWLINE = "\n";
  private static String BLANK = "";
  private static String SPACE = " ";

  public HttpRequestParser(ServerOutput serverIO, HttpParamParser paramParser) {
    this.serverIO = serverIO;
    this.paramParser = paramParser;
  }

  @Override
  public HttpRequest parseRequest(SocketConnection socket) throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String head = getHead(bufferedReader);
    String[] requestStartLine = splitRequestStartLine(head);
    HashMap<String, String> headerLines = getHeaderLines(head);

    String method = requestStartLine[0];
    String uri = getUri(requestStartLine[1]);
    HashMap<String, String> params = paramParser.getParams(requestStartLine[1]);
    String httpVersion = requestStartLine[2];
    String body = getBody(headerLines, bufferedReader);

    HttpRequest httpRequest = new HttpRequest(method, uri, params, httpVersion, headerLines, body);

    return httpRequest;
  }

  public String getHead(BufferedReader bufferedReader) {
    StringBuilder head = new StringBuilder();
    try {
      String line = bufferedReader.readLine();
      while (line != null && !line.equals("")) {
        head.append(line);
        head.append("\r\n");
        line = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return head.toString();
  }

  private String[] split(String data, String separator) {
    data = data.replace("\\", "\\\\");
    return data.split(separator);
  }

  private String getUri(String uriWithParams) {
    return uriWithParams.split("\\?")[0];
  }

  private String[] splitRequestStartLine(String head) {
    String startLine = getRequestStartLine(head);
    return startLine.split(SPACE);
  }

  private String getRequestStartLine(String head) {
    return head.split(CR)[0];
  }

  private HashMap<String, String> getHeaderLines(String head) {
    String[] headLines = split(head, NEWLINE);
    HashMap<String, String> headers = new HashMap<>();
    int length = headLines.length;

    for (int i = 1; i < headLines.length; i++) {
      String[] headerPair = headLines[i].split(": ");
      headers.put(headerPair[0], headerPair[1].trim());
    }
    return headers;
  }

  public String getBody(HashMap<String, String> headers, BufferedReader bufferedReader) {
    StringBuilder body = new StringBuilder();
    String length = headers.get("Content-Length");
    if (length != null) {
      while (body.length() < Integer.parseInt(length)) {
        try {
          body.append((char) bufferedReader.read());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return body.toString();
  }

}