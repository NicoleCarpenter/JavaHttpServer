import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MockSocket extends Socket {
  public MockSocket() throws IOException {
    super();
  }

  @Override
  public InputStream getInputStream() {
    return new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
  }
}
