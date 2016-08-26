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
} 