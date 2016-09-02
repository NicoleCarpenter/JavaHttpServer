package handler;

import io.FileIO;
import request.HttpRequest;
import response.Response;
import response.HttpResponse;
import response.HttpResponseBuilder;
import java.util.HashMap;
import java.util.Arrays;
import java.io.File;

public class FormHandler implements Handler {
  private HttpResponseBuilder responseBuilder;
  private FileIO fileIO;

  public FormHandler(HttpResponseBuilder responseBuilder, FileIO fileIO) {
    this.responseBuilder = responseBuilder;
    this.fileIO = fileIO;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    String method = request.getMethod();
    if (method.equals("GET")) {
      getForm(request);
    } else if (method.equals("POST")) {
      postForm(request);
    } else if (method.equals("PUT")) {
      putForm(request);
    } else {
      deleteForm(request);
    }
    return responseBuilder.getResponse();
  }

  public void getForm(HttpRequest request) {
    String form = generateForm("");
    File file = new File(fileIO.getRequestPath(request));
    setDefaultResponseElements();
    
    if (file.exists()) {
      responseBuilder.setBody(fileIO.getRequestPath(request));
    } else {
      responseBuilder.setBodyMessage(form);
    }
    HttpResponse response = responseBuilder.getResponse();
  }

  public void postForm(HttpRequest request) {
    fileIO.writeToFile(fileIO.getRequestPath(request), request.getBody());
    setDefaultResponseElements();
  }

  public void putForm(HttpRequest request) {
    fileIO.updateFile(fileIO.getRequestPath(request), request.getBody());
    setDefaultResponseElements();
  }

  public void deleteForm(HttpRequest request) {
    fileIO.deleteFileContent(fileIO.getRequestPath(request));
    setDefaultResponseElements();
  }

  private String generateForm(String value) {
    return "<!DOCTYPE html>\n" + 
           "<html>\n" + 
           "<body>\n" +
           "<form action=\"/form\" method=\"post\">" +
           "<input name=\"data\" type=\"text\">" +
           "<input type=\"submit\" value=\"submit\">" +
           "</form>" + 
           value + 
           "</body></html>";
  }

  private void setDefaultResponseElements() {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
  }
}