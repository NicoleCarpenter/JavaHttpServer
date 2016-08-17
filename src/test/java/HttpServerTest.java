import com.carpentern.*;

public class HttpServerTest extends junit.framework.TestCase {
  MockHttpServerSocket serverSocket;
  MockHttpRequestParser requestParser;
  MockHttpHandler handler;
  MockHttpServerIO serverIO;
  HttpServer server;
  
  protected void setUp() {
    serverSocket = new MockHttpServerSocket();
    requestParser = new MockHttpRequestParser();
    handler = new MockHttpHandler();
    serverIO = new MockHttpServerIO();
    server = new HttpServer(serverSocket, requestParser, handler, serverIO);
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
    assertTrue(handler.handleRouteCalled());
  }

  public void testWriteResponseCalled() {
    assertTrue(serverIO.writeResponseCalled());
  }

}