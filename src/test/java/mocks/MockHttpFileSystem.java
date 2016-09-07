import file.FileSystem;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MockHttpFileSystem implements FileSystem {
  private boolean existsCalled = false;
  private boolean isFileCalled = false;
  private boolean listFilesCalled = false;
  private boolean listCalled = false;
  private boolean getNameCalled = false;
  private boolean getAbsolutePathCalled = false;
  private boolean stubbedExists;
  private boolean stubbedIsFile;
  private File[] stubbedListFiles;
  private String[] stubbedList;
  private String stubbedGetName;
  private String stubbedGetAbsolutePath;

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