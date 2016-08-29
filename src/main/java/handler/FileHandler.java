package com.carpentern;

import java.util.HashMap;
import java.util.Arrays;
import java.io.File;

public class FileHandler implements Handler {
  private HttpResponseBuilder responseBuilder;
  private String path;
  private String uri;
  private FileSystem fileSystem;
  private final String htmlHead = "<!DOCTYPE html><html><body>";
  private final String htmlFoot = "</body></html>";

  public FileHandler(HttpResponseBuilder responseBuilder, String path, String uri, FileSystem fileSystem) {
    this.responseBuilder = responseBuilder;
    this.path = path;
    this.uri = uri;
    this.fileSystem = fileSystem;
  }

  @Override
  public Response handleRoute(HttpRequest request) {    
    if (fileSystem.isFile(path)) {
      return buildFileResponse();
    } else if (fileSystem.isDirectory(path)) {
      return buildDirectoryResponse();
    } else {
      return null;
    }
  }

  private HttpResponse buildFileResponse() {
    setDefaultResponseElements();
    responseBuilder.setBody(path);
    setFileTypeHeaders();
    return responseBuilder.getResponse();
  }

  private HttpResponse buildDirectoryResponse() {
    responseBuilder.setBodyMessage(htmlHead + buildFileLinks() + htmlFoot);
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

  private void setDefaultResponseElements() {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
  }

  private void setFileTypeHeaders() {
    int dotPosition = uri.lastIndexOf(".");
    String extension = "";
    if (hasExtension(dotPosition)) {
      responseBuilder.setHeader("Content-Type", findFileType(getExtension(dotPosition)));
      responseBuilder.setHeader("Content-Length", responseBuilder.getContentLength());
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
} 