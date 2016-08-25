package com.carpentern;

import java.io.File;

public class HttpFileSystem implements FileSystem {
  String uri;
  File file;

  public HttpFileSystem(String uri) {
    this.uri = uri;
    file = new File(uri);
  }

  public boolean exists() {
    return file.exists();
  }

  public boolean isFile() {
    return file.isFile();
  }

  public boolean isDirectory() {
    return file.isDirectory();
  }

  public File[] listFiles() {
    return file.listFiles();
  }

  public String getName() {
    return file.getName();
  }
}