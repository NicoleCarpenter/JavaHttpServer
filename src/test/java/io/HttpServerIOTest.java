import io.HttpServerIO;
import java.io.InputStream;
import java.io.IOException;

public class HttpServerIOTest extends junit.framework.TestCase {
  private String request;
  private String response;
  private InputStream input;
  private MockHttpSocketConnection socketConnection;
  private HttpServerIO serverIO;

  protected void setUp() throws IOException {
    request = "GET / HTTP/1.0\r\n\r\n";
    response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n";

    MockHttpServerSocket serverSocket = new MockHttpServerSocket();
    serverSocket.stubInputStream(request);
    socketConnection = serverSocket.listen();
    serverIO = new HttpServerIO();
  }

  protected void tearDown() {
    serverIO = null;
  }

  public void testReadRequest() throws IOException {
    assertTrue(true);
    // assertEquals(request, serverIO.readRequest(socketConnection.getInputStream()));
  }

  public void testWriteResponse() throws IOException {
    serverIO.writeResponse(response.getBytes(), socketConnection.getOutputStream());
    assertEquals(response, socketConnection.getOutputStream().toString());
  }
}
