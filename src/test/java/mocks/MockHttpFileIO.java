import io.FileIO;
import request.HttpRequest;
import java.io.File;

public class MockHttpFileIO implements FileIO {
  String stubbedResponseBody;
  Boolean deleteFileContentCalled = false;
  Boolean getFileContentsCalled = false;
  Boolean getRootDirectoryCalled = false;
  Boolean updateFileCalled = false;
  Boolean writeToFileCalled = false;

  public byte[] getFileContents(String file) {
    getFileContentsCalled = true;
    return stubbedResponseBody.getBytes();
  }

  public byte[] getPartialFileContents(String filePath, String range) {
    return stubbedResponseBody.getBytes();
  }

  public void writeToFile(String filePath, String content) {
    writeToFileCalled = true;
  }

  public void updateFile(String filePath, String content) {
    updateFileCalled = true;
  }

  public void deleteFileContent(String fileName) {
    deleteFileContentCalled = true;
  }

  public void stubResponseBody(String responseBody) {
    stubbedResponseBody = responseBody;
  }

  public File getRootDirectory() {
    getRootDirectoryCalled = true;
    return new File("/");
  }

}