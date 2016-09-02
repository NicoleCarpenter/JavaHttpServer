package io;

import request.HttpRequest;
import java.io.File;

public interface FileIO {
  public abstract byte[] getFileContents(String file);
  public abstract String getRequestPath(HttpRequest request);
  public abstract void writeToFile(String filePath, String content);
  public abstract void updateFile(String filePath, String content);
  public abstract void deleteFileContent(String fileName);
}