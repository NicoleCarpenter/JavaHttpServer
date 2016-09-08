import file.FileSystem;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MockHttpFileSystem implements FileSystem {
  boolean existsCalled = false;
  boolean isFileCalled = false;
  boolean listFilesCalled = false;
  boolean listCalled = false;
  boolean getNameCalled = false;
  boolean getAbsolutePathCalled = false;
  boolean stubbedExists;
  boolean stubbedIsFile;
  File[] stubbedListFiles;
  String[] stubbedList;
  String stubbedGetName;
  String stubbedGetAbsolutePath;

  public boolean exists(String file) {
    existsCalled = true;
    return stubbedExists;
  }

  public boolean isFile(String file) {
    isFileCalled = true;
    return stubbedIsFile;
  }

  public File[] listFiles(String directory) {
    listFilesCalled = true;
    return stubbedListFiles;
  }

  public String[] list(String directory) {
    listFilesCalled = true;
    return stubbedList;
  }

  public String getName(String file) {
    getNameCalled = true;
    return stubbedGetName;
  }

  public String getAbsolutePath(String file) {
    getAbsolutePathCalled = true;
    return stubbedGetAbsolutePath;
  }

  public void stubExists(boolean stubbedValue) {
    stubbedExists = stubbedValue;
  }

  public void stubIsFile(boolean stubbedValue) {
    stubbedIsFile = stubbedValue;
  }

  public void stubListFiles(File[] stubbedValue) {
    // stubbedListFiles.add(stubbedValue);
  }

  public void stubGetName(String stubbedValue) {
    stubbedGetName = stubbedValue;
  }

  public void stubbedGetAbsolutePath(String stubbedValue) {
    stubbedGetAbsolutePath = stubbedValue;
  }

}