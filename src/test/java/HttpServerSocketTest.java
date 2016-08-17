import com.carpentern.*;

import java.net.Socket;
import java.io.IOException;

public class HttpServerSocketTest extends junit.framework.TestCase {
  MockServerSocket serverSocket;
  ServerSocketInterface httpServerSocket;

  protected void setUp() throws IOException {
    serverSocket = new MockServerSocket();
    httpServerSocket = new HttpServerSocket(serverSocket);
  }

  public void testListen() throws IOException {
    Socket socket = serverSocket.accept();
  }
}