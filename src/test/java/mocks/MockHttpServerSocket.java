import com.carpentern.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockHttpServerSocket implements ServerSocketInterface {
  String stubbedInputStream = "";
  boolean listenCalled = false;

  public MockHttpSocketConnection listen() throws IOException {
    listenCalled = true;
    InputStream inputStream = new ByteArrayInputStream(stubbedInputStream.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    return new MockHttpSocketConnection(inputStream, outputStream);
  }

  public void stubInputStream(String inputStream) {
    stubbedInputStream = inputStream;
  }

  public boolean listenCalled() {
    return listenCalled;
  }
 
}