package com.carpentern;

import java.io.File;

public class HttpFileSystem implements FileSystem {
  private String uri;
  private File file;

  public HttpFileSystem(String uri) {
    this.uri = uri;
    file = new File(uri);
  }

  @Override
  public boolean exists() {
    return file.exists();
  }

  @Override
  public boolean isFile() {
    return file.isFile();
  }

  @Override
  public boolean isDirectory() {
    return file.isDirectory();
  }

  @Override
  public File[] listFiles() {
    return file.listFiles();
  }

  @Override
  public String getName() {
    return file.getName();
  }
}