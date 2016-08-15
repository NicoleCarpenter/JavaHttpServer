import com.carpentern.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MockHttpServerIO implements ServerIO {
  boolean readRequestCalled = false;
  boolean writeResponseCalled = false;

  public String readRequest(InputStream input) throws IOException {
    readRequestCalled = true;
    return "Hello World";
  }

  public void writeResponse(byte[] response, OutputStream output) throws IOException {
    writeResponseCalled = true;
  }

  public boolean readRequestCalled() {
    return readRequestCalled;
  }

  public boolean writeResponseCalled() {
    return writeResponseCalled;
  }
}