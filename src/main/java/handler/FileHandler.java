package handler;

import io.FileIO;
import file.FileSystem;
import request.HttpRequest;
import response.HttpResponse;
import response.ResponseBuilder;
import util.FileTypeMatcher;
import util.HtmlFormatter;
import java.util.HashMap;

public class FileHandler implements Handler {
  private ResponseBuilder responseBuilder;
  private FileSystem fileSystem;
  private FileIO fileIO;
  private FileTypeMatcher typeMatcher;

  public FileHandler(ResponseBuilder responseBuilder, FileSystem fileSystem, FileIO fileIO, FileTypeMatcher typeMatcher) {
    this.responseBuilder = responseBuilder;
    this.fileSystem = fileSystem;
    this.fileIO = fileIO;
    this.typeMatcher = typeMatcher;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String uri = request.getUri();
    String path = findPath(request, uri);
    if (fileSystem.isFile(path)) {
      buildFileResponse(request, path, uri);
    } else {
      buildDirectoryResponse(path, uri);
    }
    return responseBuilder.getResponse();
  }

  private void buildFileResponse(HttpRequest request, String path, String uri) {
    if (isPartialFileRequest(request)) {
      buildPartialFileResponse(request, path);
    } else {
      buildFullFileResponse(path);
    }
    setFileTypeHeaders(uri);
  }

  private boolean isPartialFileRequest(HttpRequest request) {
    HashMap<String, String> headers = request.getHeaderLines();
    return headers.containsKey("Range");
  }

  private void buildPartialFileResponse(HttpRequest request, String path) {
    String range = getRange(request);
    byte[] partialFileContent = fileIO.getPartialFileContents(path, range);
    responseBuilder.buildPartialFileResponse(range);
    responseBuilder.setBody(partialFileContent);
  }

  private String getRange(HttpRequest request) {
    return request.getHeaderLines().get("Range");
  }

  private void buildFullFileResponse(String path) {
    byte[] fileContent = fileIO.getFileContents(path);
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(fileContent);
  }

  private void buildDirectoryResponse(String path, String uri) {
    HtmlFormatter formatter = new HtmlFormatter();
    String[] files = fileSystem.list(path);
    byte[] directoryContents = formatter.getFormattedDirectoryFiles(files, uri);
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(directoryContents);
  }

  private void setFileTypeHeaders(String uri) {
    int dotPosition = findDotPosition(uri);
    if (hasExtension(dotPosition)) {
      responseBuilder.setHeader("Content-Type", typeMatcher.getFileType(uri));
    }
  }

  private int findDotPosition(String uri) {
    return uri.lastIndexOf(".");
  }

  private boolean hasExtension(int dotPosition) {
    return dotPosition != -1;
  }

  private String findPath(HttpRequest request, String uri) {
    String rootPath = fileSystem.getFileAbsolutePath(fileIO.getRootDirectory());
    String requestPath = uri.replace(fileSystem.getFileName(fileIO.getRootDirectory()), "");
    return rootPath + requestPath;
  }

} 