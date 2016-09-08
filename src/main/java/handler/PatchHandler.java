package handler;

import file.FileSystem;
import io.FileIO;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.io.File;

public class PatchHandler implements Handler {
  private HttpResponseBuilder responseBuilder;
  private FileIO fileIO;
  private String etag;
  private String defaultEtag;

  public PatchHandler(HttpResponseBuilder responseBuilder, FileIO fileIO) {
    this.responseBuilder = responseBuilder;
    this.fileIO = fileIO;
    this.etag = "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";
    this.defaultEtag = "5c36acad75b78b82be6d9cbbd6143ab7e0cc04b0";
  }

  @Override
  public Response handleRoute(HttpRequest request) {
    String path = findPath(request);
    String requestEtag = getRequestEtag(request);
    fileIO.writeToFile(path, request.getBody());

    if (isMatchingPatchTag(requestEtag)) {
      buildPatchedContentResponse();
    } else {
      buildFullFileContentResponse(path);
    }

    responseBuilder.setHeader("Etag", etag);
    etag = defaultEtag;
    return responseBuilder.getResponse();
  }

  private String getRequestEtag(HttpRequest request) {
    return request.getHeaderLines().get("If-Match");
  }

  private boolean isMatchingPatchTag(String requestEtag) {
    return requestEtag.trim().equals(etag.trim());
  }

  private void buildPatchedContentResponse() {
    byte[] emptyBody = new String("").getBytes();
    responseBuilder.buildPatchedContentResponse();
    responseBuilder.setBody(emptyBody);
  }

  private void buildFullFileContentResponse(String path) {
      byte[] fileContent = fileIO.getFileContents(path);
      responseBuilder.buildOkResponse();
      responseBuilder.setBody(fileContent);
  }

  private String findPath(HttpRequest request) {
    String uri = request.getUri();
    File rootDirectory = fileIO.getRootDirectory();
    String rootPath = rootDirectory.getAbsolutePath();
    String requestPath = uri.replace(rootDirectory.getName(), "");
    return rootPath + requestPath;
  }

}
