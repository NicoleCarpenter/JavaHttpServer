package carpentern.coreServer.request;

import carpentern.coreServer.parser.HttpParamParser;
import carpentern.coreServer.socket.SocketConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.io.IOException;

public class HttpRequestParser implements RequestParser {
  private HttpParamParser paramParser;
  private static String CR = "\r\n";

  public HttpRequestParser(HttpParamParser paramParser) {
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

    return new HttpRequest(method, uri, params, httpVersion, headerLines, body);
  }

  private String getHead(BufferedReader bufferedReader) {
    StringBuilder head = new StringBuilder();
    try {
      String line = bufferedReader.readLine();
      while (line != null && !line.equals("")) {
        head.append(line);
        head.append(CR);
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
    String SPACE = " ";
    String startLine = getRequestStartLine(head);
    return startLine.split(SPACE);
  }

  private String getRequestStartLine(String head) {
    return head.split(CR)[0];
  }

  private HashMap<String, String> getHeaderLines(String head) {
    String NEWLINE = "\n";
    String[] headLines = split(head, NEWLINE);
    HashMap<String, String> headers = new HashMap<>();

    for (int i = 1; i < headLines.length; i++) {
      String[] headerPair = headLines[i].split(": ");
      headers.put(headerPair[0], headerPair[1].trim());
    }
    return headers;
  }

  private String getBody(HashMap<String, String> headers, BufferedReader bufferedReader) {
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