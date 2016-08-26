import com.carpentern.*;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MockHttpFileSystem implements FileSystem {
  private boolean existsCalled = false;
  private boolean isFileCalled = false;
  private boolean isDirectoryCalled = false;
  private boolean listFilesCalled = false;
  private boolean getNameCalled = false;
  private boolean stubbedExists;
  private boolean stubbedIsFile;
  private boolean stubbedIsDirectory;
  private File[] stubbedListFiles;
  private String stubbedGetName;

  public boolean exists() {
    existsCalled = true;
    return stubbedExists;
  }

  public boolean isFile() {
    isFileCalled = true;
    return stubbedIsFile;
  }

  public boolean isDirectory() {
    isDirectoryCalled = true;
    return stubbedIsDirectory;
  }

  public File[] listFiles() {
    listFilesCalled = true;
    return stubbedListFiles;
  }

  public String getName() {
    getNameCalled = true;
    return stubbedGetName;
  }

  public void stubExists(boolean stubbedValue) {
    stubbedExists = stubbedValue;
  }

  public void stubIsFile(boolean stubbedValue) {
    stubbedIsFile = stubbedValue;
  }

  public void stubIsDirectory(boolean stubbedValue) {
    stubbedIsDirectory = stubbedValue;
  }

  public void stubListFiles(File[] stubbedValue) {
    // stubbedListFiles.add(stubbedValue);
  }

  public void stubGetName(String stubbedValue) {
    stubbedGetName = stubbedValue;
  }

}