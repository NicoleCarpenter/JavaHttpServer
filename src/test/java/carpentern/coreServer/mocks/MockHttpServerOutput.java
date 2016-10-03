import carpentern.coreServer.io.ServerOutput;

import java.io.OutputStream;
import java.io.IOException;

class MockHttpServerOutput implements ServerOutput {
  boolean writeResponseCalled = false;

  public void writeResponse(byte[] response, OutputStream output) throws IOException {
    writeResponseCalled = true;
  }

}