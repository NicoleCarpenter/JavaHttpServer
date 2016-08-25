package com.carpentern;

import java.io.File;

public interface FileIO {
  public abstract byte[] getFileContents(String file);
}