import carpentern.coreServer.socket.SocketConnection;
import java.io.InputStream;
import java.io.OutputStream;

class MockHttpSocketConnection implements SocketConnection {
  boolean getInputStreamCalled = false;
  boolean getOutputStreamCalled = false;
  InputStream inputStream;
  OutputStream outputStream;

  MockHttpSocketConnection(InputStream inputStream, OutputStream outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  public InputStream getInputStream() {
    getInputStreamCalled = true;
    return inputStream;
  }

  public OutputStream getOutputStream() {
    getOutputStreamCalled = true;
    return outputStream;
  }
}
