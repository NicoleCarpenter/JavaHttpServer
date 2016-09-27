import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class MockServerSocket extends ServerSocket {
  public MockServerSocket() throws IOException {
    super();
  }

  @Override
  public Socket accept() throws IOException {
    return new MockSocket();
  }

}