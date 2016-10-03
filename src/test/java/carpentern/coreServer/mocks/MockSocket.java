import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

class MockSocket extends Socket {

  MockSocket() throws IOException {
    super();
  }

  @Override
  public InputStream getInputStream() {
    return new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
  }
}
