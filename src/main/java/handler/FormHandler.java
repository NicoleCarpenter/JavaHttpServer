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
    String path = findPath(request);

    if (method.equals("GET")) {
      getForm(request, path);
    } else if (method.equals("POST")) {
      postForm(request);
    } else if (method.equals("PUT")) {
      putForm(request);
    } else {
      deleteForm(request);
    }
    return responseBuilder.getResponse();
  }

  public void getForm(HttpRequest request, String path) {
    String form = generateForm("");
    File file = new File(path);
    responseBuilder.buildOkResponse();
    
    if (file.exists()) {
      byte[] fileContent = fileIO.getFileContents(path);
      responseBuilder.setBody(fileContent);
    } else {
      byte[] formContent = new String(form).getBytes();
      responseBuilder.setBody(formContent);
    }
    HttpResponse response = responseBuilder.getResponse();
  }

  public void postForm(HttpRequest request) {
    fileIO.writeToFile(fileIO.getRequestPath(request), request.getBody());
    responseBuilder.buildOkResponse();
  }

  public void putForm(HttpRequest request) {
    fileIO.updateFile(fileIO.getRequestPath(request), request.getBody());
    responseBuilder.buildOkResponse();
  }

  public void deleteForm(HttpRequest request) {
    fileIO.deleteFileContent(fileIO.getRequestPath(request));
    responseBuilder.buildOkResponse();
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

  private String findPath(HttpRequest request) {
    String uri = request.getUri();  
    File rootDirectory = fileIO.getRootDirectory();
    String rootPath = rootDirectory.getAbsolutePath();
    String requestPath = uri.replace(rootDirectory.getName(), "");
    return rootPath + requestPath;
  }
}