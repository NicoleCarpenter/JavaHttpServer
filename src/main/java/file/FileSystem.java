package com.carpentern;

import java.io.File;

public interface FileSystem {
  public abstract boolean exists();
  public abstract boolean isFile();
  public abstract boolean isDirectory();
  public abstract File[] listFiles();
  public abstract String getName();
}