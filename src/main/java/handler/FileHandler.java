package handler;

import io.FileIO;
import file.FileSystem;
import request.HttpRequest;
import response.Response;
import response.HttpResponse;
import response.HttpResponseBuilder;
import java.util.HashMap;
import java.util.Arrays;
import java.io.File;

public class FileHandler implements Handler {
  private HttpResponseBuilder responseBuilder;
  private FileSystem fileSystem;
  private FileIO fileIO;
  private String uri;
  private String path;
  private final String htmlHead = "<!DOCTYPE html><html><body>";
  private final String htmlFoot = "</body></html>";

  public FileHandler(HttpResponseBuilder responseBuilder, FileSystem fileSystem, FileIO fileIO) {
    this.responseBuilder = responseBuilder;
    this.fileIO = fileIO;
    this.fileSystem = fileSystem;
    this.uri = "";
    this.path = "";
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    setUri(request.getUri());
    setPath(findPath(request));
    
    if (fileSystem.isFile(path)) {
      return buildFileResponse(request);
    } else if (fileSystem.isDirectory(path)) {
      return buildDirectoryResponse();
    } else {
      return null;
    }
  }

  private HttpResponse buildFileResponse(HttpRequest request) {
    if (isPartialFileRequest(request)) {
      buildPartialFileResponse(request);
    } else {
      buildFullFileResponse();
    }
    setFileTypeHeaders();
    return responseBuilder.getResponse();
  }

  private boolean isPartialFileRequest(HttpRequest request) {
    HashMap<String, String> headers = request.getHeaderLines();
    return headers.containsKey("Range");
  }

  private void buildPartialFileResponse(HttpRequest request) {
    String range = getRange(request);
    byte[] partialFileContent = fileIO.getPartialFileContents(path, range);
    responseBuilder.buildPartialFileResponse(range);
    responseBuilder.setBody(partialFileContent);
  }

  private String getRange(HttpRequest request) {
    return request.getHeaderLines().get("Range");
  }

  private void buildFullFileResponse() {
    byte[] fileContent = fileIO.getFileContents(path);
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(fileContent);
  }

  private HttpResponse buildDirectoryResponse() {
    byte[] directoryContents = new String(htmlHead + buildFileLinks() + htmlFoot).getBytes();
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(directoryContents);
    return responseBuilder.getResponse();
  }

  private String buildFileLinks() {
    String[] files = fileSystem.list(path);
    StringBuilder directoryContents = new StringBuilder();
    for (String file : files) {
      directoryContents.append(buildLink(file));
    }
    return directoryContents.toString();
  }

  private String buildLink(String file) {
    String pathSlash = "";
    if (!uri.endsWith("/")) {
      pathSlash = "/";
    }
    return "<a href=\"" + uri + pathSlash + file + "\">" + file + "</a><br>";
  }
  
  private void setFileTypeHeaders() {
    int dotPosition = uri.lastIndexOf(".");
    String extension = "";
    if (hasExtension(dotPosition)) {
      responseBuilder.setHeader("Content-Type", findFileType(getExtension(dotPosition)));
    }
  }

  private String getExtension(int dotPosition) {
    return uri.substring(dotPosition);
  }

  private boolean hasExtension(int dotPosition) {
    return dotPosition != -1;
  }

  private String findFileType(String extension) {
    if (isText(extension)) {
      return "text/html";
    } else if (isImageJpg(extension)) {
      return "image/jpeg";
    } else if (isImageGif(extension)) {
      return "image/gif";
    } else if (isImagePng(extension)) {
      return "image/png";
    } else {
      return "application/octet-stream";
    }
  }

  private boolean isText(String extension) {
    return extension.equals(".htm") || extension.equals(".html") || extension.equals(".txt");
  }

  private boolean isImageJpg(String extension) {
    return extension.equals(".jpg") || extension.equals(".jpeg");
  }

  private boolean isImageGif(String extension) {
    return extension.equals(".gif");
  }

  private boolean isImagePng(String extension) {
    return extension.equals(".png");
  }

  private String findPath(HttpRequest request) {
    String uri = request.getUri();  
    File rootDirectory = fileIO.getRootDirectory();
    String rootPath = rootDirectory.getAbsolutePath();
    String requestPath = uri.replace(rootDirectory.getName(), "");
    return rootPath + requestPath;
  }

  private void setUri(String uri) {
    this.uri = uri;
  }

  private void setPath(String path) {
    this.path = path;
  }
} 