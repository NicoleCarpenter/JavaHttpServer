import com.carpentern.*;

public class HttpServerTest extends junit.framework.TestCase {
  private MockHttpServerSocket serverSocket;
  private MockHttpRequestParser requestParser;
  private MockHttpRouter router;
  private MockHttpServerIO serverIO;
  private HttpServer server;
  
  protected void setUp() {
    serverSocket = new MockHttpServerSocket();
    requestParser = new MockHttpRequestParser();
    router = new MockHttpRouter();
    serverIO = new MockHttpServerIO();
    server = new HttpServer(serverSocket, requestParser, router, serverIO);
    server.run();
  }

  protected void tearDown() {
    server = null;
  }

  public void testListenCalled() {
    assertTrue(serverSocket.listenCalled());
  }

  public void testParseRequestCalled() {
    assertTrue(requestParser.parseRequestCalled());
  }

  public void testHandleRouteCalled() {
    assertTrue(router.getRouteCalled());
  }

  public void testWriteResponseCalled() {
    assertTrue(serverIO.writeResponseCalled());
  }

}