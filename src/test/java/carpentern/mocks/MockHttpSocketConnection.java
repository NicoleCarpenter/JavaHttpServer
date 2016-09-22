import carpentern.socket.SocketConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MockHttpSocketConnection implements SocketConnection {
  boolean getInputStreamCalled = false;
  boolean getOutputStreamCalled = false;
  InputStream inputStream;
  OutputStream outputStream;

  public MockHttpSocketConnection(InputStream inputStream, OutputStream outputStream) {
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
