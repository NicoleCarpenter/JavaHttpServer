package response;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpResponse implements Response {
  private static String CRLF = "\r\n\r\n";
  private static String CR = "\r\n";
  private String httpVersion = "HTTP/1.1";
  private String statusCode;
  private String statusMessage;
  private HashMap<String, String> headerLines = new HashMap<String, String>();
  private byte[] body;

  @Override
  public String getHttpVersion() {
    return httpVersion;
  }

  @Override
  public String getStatusCode() {
    return statusCode;
  }

  @Override
  public String getStatusMessage() {
    return statusMessage;
  }

  @Override
  public HashMap<String, String> getHeaderLines() {
    return headerLines;
  }

  @Override
  public byte[] getBody() {
    return body;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public void setHeader(String key, String value) {
    headerLines.put(key, value);
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  @Override
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
