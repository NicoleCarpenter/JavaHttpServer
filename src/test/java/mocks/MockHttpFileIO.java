import com.carpentern.*;

import java.io.File;

public class MockHttpFileIO implements FileIO {
  String stubbedResponseBody;

  public byte[] getFileContents(String file) {
    return stubbedResponseBody.getBytes();
  }

  public void stubResponseBody(String responseBody) {
    stubbedResponseBody = responseBody;
  }
}