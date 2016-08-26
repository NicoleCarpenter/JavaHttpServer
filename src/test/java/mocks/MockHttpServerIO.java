import com.carpentern.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MockHttpServerIO implements ServerIO {
  private boolean readRequestCalled = false;
  private boolean writeResponseCalled = false;
  private String stubbedRequest;

  public String readRequest(InputStream input) throws IOException {
    readRequestCalled = true;
    return stubbedRequest;
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

  public void stubRequest(String request) {
    stubbedRequest = request;
  }
}