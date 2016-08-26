package com.carpentern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HttpFileIO implements FileIO {
  private FileInputStream fileInputStream = null;
  private FileOutputStream fileOutputStream = null;

  @Override
  public byte[] getFileContents(String fileName) {
    File file = new File(fileName);
    byte[] fileContent = new byte[(int) file.length()];
    try {
      fileInputStream = new FileInputStream(file);
      fileInputStream.read(fileContent);
      fileInputStream.close();
    } catch (FileNotFoundException  e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileContent;
  }

}