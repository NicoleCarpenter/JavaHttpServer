package response;

import io.FileIO;
import request.HttpRequest;

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

  public void setBody(String filePath) {
    response.setBody(fileIO.getFileContents(filePath));
  }

  public void setBodyEmpty() {
    response.setBody(new String("").getBytes());
  }

  public void setBodyMessage(String message) {
    response.setBody(new String(message).getBytes());
  }

  public void setBodyPartial(String filePath, String range) {
    response.setBody(fileIO.getPartialFileContents(filePath, range));
  }

  public HttpResponse getResponse() {
    return response;
  }

  public String getContentLength() {
    String body = response.bodyToString();
    return Integer.toString(body.length());
  }

}