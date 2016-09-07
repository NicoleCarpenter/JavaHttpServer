import io.FileIO;
import request.HttpRequest;
import java.io.File;

public class MockHttpFileIO implements FileIO {
  String stubbedResponseBody;

  public byte[] getFileContents(String file) {
    return stubbedResponseBody.getBytes();
  }

  public byte[] getPartialFileContents(String filePath, String range) {
    return stubbedResponseBody.getBytes();
  }

  public void writeToFile(String filePath, String content) {

  }

  public void updateFile(String filePath, String content) {

  }

  public void deleteFileContent(String fileName) {

  }

  public void stubResponseBody(String responseBody) {
    stubbedResponseBody = responseBody;
  }

  public File getRootDirectory() {
    return new File("/");
  }

}