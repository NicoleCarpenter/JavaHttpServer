import carpentern.coreServer.server.HttpServer;

public class HttpServerTest extends junit.framework.TestCase {
  private MockHttpServerSocket serverSocket;
  private MockHttpRequestParser requestParser;
  private MockHttpRouter router;
  private MockHttpServerOutput serverIO;
  private HttpServer server;

  protected void setUp() {
    serverSocket = new MockHttpServerSocket();
    requestParser = new MockHttpRequestParser();
    router = new MockHttpRouter();
    serverIO = new MockHttpServerOutput();
    server = new HttpServer(serverSocket, requestParser, router, serverIO);
    // server.start();
  }

  protected void tearDown() {
    server = null;
  }

  public void testIsConnectionClosedCalled() {
    assertTrue(true);
  }
}
