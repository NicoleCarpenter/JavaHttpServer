import socket.ServerSocketInterface;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockHttpServerSocket implements ServerSocketInterface {
  private String stubbedInputStream;
  private boolean listenCalled = false;
  private boolean stubbedIsConnectionClosed;
  private boolean isConnectionClosedCalled = false;

  public MockHttpSocketConnection listen() throws IOException {
    listenCalled = true;
    InputStream inputStream = new ByteArrayInputStream(stubbedInputStream.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    return new MockHttpSocketConnection(inputStream, outputStream);
  }

  public boolean isConnectionClosed() throws IOException {
    isConnectionClosedCalled = true;
    return stubbedIsConnectionClosed;
  }

  public void stubInputStream(String inputStream) {
    stubbedInputStream = inputStream;
  }

  public boolean listenCalled() {
    return listenCalled;
  }

  public boolean isConnectionClosedCalled() {
    return stubbedIsConnectionClosed;
  }

  public void stubIsConnectionClosed(boolean value) {
    stubbedIsConnectionClosed = value;
  }

}
