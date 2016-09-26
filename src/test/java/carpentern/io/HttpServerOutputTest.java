import carpentern.coreServer.io.HttpServerOutput;
import java.io.InputStream;
import java.io.IOException;

public class HttpServerOutputTest extends junit.framework.TestCase {
  private String request;
  private String response;
  private InputStream input;
  private MockHttpSocketConnection socketConnection;
  private HttpServerOutput serverOutput;

  protected void setUp() throws IOException {
    request = "GET / HTTP/1.0\r\n\r\n";
    response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n";

    MockHttpServerSocket serverSocket = new MockHttpServerSocket();
    serverSocket.stubInputStream(request);
    socketConnection = serverSocket.listen();
    serverOutput = new HttpServerOutput();
  }

  protected void tearDown() {
    serverOutput = null;
  }

  public void testWriteResponse() throws IOException {
    serverOutput.writeResponse(response.getBytes(), socketConnection.getOutputStream());
    assertEquals(response, socketConnection.getOutputStream().toString());
  }
}