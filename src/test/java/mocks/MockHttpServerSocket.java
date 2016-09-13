import socket.ServerSocketInterface;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockHttpServerSocket implements ServerSocketInterface {
  String stubbedInputStream;
  boolean listenCalled = false;
  boolean stubbedIsConnectionClosed;
  boolean isConnectionClosedCalled = false;

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

  public void stubIsConnectionClosed(boolean value) {
    stubbedIsConnectionClosed = value;
  }

}
