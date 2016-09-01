package com.carpentern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class HttpFileIO implements FileIO {
  private File rootDirectory;
  private FileInputStream fileInputStream = null;
  private FileOutputStream fileOutputStream = null;

  public HttpFileIO(File rootDirectory) {
    this.rootDirectory = rootDirectory;
  }

  @Override
  public byte[] getFileContents(String filePath) {
    File file = new File(filePath);
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

  public File createFile(String uri) {
    File file = new File(rootDirectory.getAbsolutePath() + uri);
    try {
      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return file;
  }

  public void writeToFile(String filePath, String content) {
    try {
      PrintWriter writer = new PrintWriter(filePath, "UTF-8");
      writer.println(content);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void updateFile(String filePath, String content) {
    File file = new File(filePath);
    BufferedWriter writer;
    try {
      writer = new BufferedWriter(new FileWriter(file));
      writer.write(content);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void deleteFileContent(String fileName){
    File file = new File(fileName);
    try {
      fileOutputStream = new FileOutputStream(file);
      fileOutputStream.write("".getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getRequestPath(HttpRequest request) {
    return rootDirectory.getAbsolutePath() + request.getPathFromRoot(rootDirectory);
  }

}