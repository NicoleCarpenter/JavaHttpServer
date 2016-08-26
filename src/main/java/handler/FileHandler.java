package com.carpentern;

import java.util.HashMap;
import java.util.Arrays;
import java.io.File;

public class FileHandler implements Handler {
  private HttpResponseBuilder responseBuilder;
  private FileSystem fileSystem;

  public FileHandler(HttpResponseBuilder responseBuilder, FileSystem fileSystem) {
    this.responseBuilder = responseBuilder;
    this.fileSystem = fileSystem;
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    String uri = request.getUri();
    if (fileSystem.isFile()) {
      return buildFileResponse(uri);
    } else if (fileSystem.isDirectory()) {
      return buildDirectoryResponse();
    } else {
      return null;
    }
  }

  private HttpResponse buildFileResponse(String uri) {
    setDefaultResponseElements();
    responseBuilder.setBody(uri);
    return responseBuilder.getResponse();
  }

  private HttpResponse buildDirectoryResponse() {
    File[] files = fileSystem.listFiles();
    StringBuilder directoryContents = new StringBuilder();

    directoryContents.append(fileSystem.getName());
    for (File file : files) {
      directoryContents.append(file);
    }
    responseBuilder.setBodyMessage(directoryContents.toString());
    return responseBuilder.getResponse();
  }

  private void setDefaultResponseElements() {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
  }
}