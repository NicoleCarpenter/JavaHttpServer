package carpentern.coreServer.response;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpResponse {
  private static String CR = "\r\n";
  private String httpVersion = "HTTP/1.1";
  private String statusCode;
  private String statusMessage;
  private HashMap<String, String> headerLines = new HashMap<String, String>();
  private byte[] body = new byte[0];

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

  public byte[] getBody() {
    return body;
  }

  void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  void setHeader(String key, String value) {
    headerLines.put(key, value);
  }

  void setBody(byte[] body) {
    this.body = body;
  }

  public byte[] getFormattedResponse() throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write((httpVersion + " " +
                        statusCode + " " +
                        statusMessage +
                        CR +
                        headersToString() +
                        CR).getBytes());
    outputStream.write(body);
    return outputStream.toByteArray();
  }

  private String headersToString() {
    StringBuilder builder = new StringBuilder();
    headerLines.forEach((key, value)-> builder.append(key + ": " + value + CR));
    return builder.toString();
  }

}
