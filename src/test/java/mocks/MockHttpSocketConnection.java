import socket.SocketConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MockHttpSocketConnection implements SocketConnection {
  private boolean getInputStreamCalled = false;
  private boolean getOutputStreamCalled = false;
  private InputStream inputStream;
  private OutputStream outputStream;

  public MockHttpSocketConnection(InputStream inputStream, OutputStream outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  public InputStream getInputStream() {
    getInputStreamCalled = true;
    return inputStream;
  }

  public boolean getInputStreamCalled() {
    return getInputStreamCalled;
  }

  public OutputStream getOutputStream() {
    getOutputStreamCalled = true;
    return outputStream;
  }
  
  public boolean getOutputStreamCalled() {
    return getOutputStreamCalled;
  }
}