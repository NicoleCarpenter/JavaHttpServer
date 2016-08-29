import com.carpentern.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HttpServerRunnerTest extends junit.framework.TestCase {
  private MockHttpSocketConnection socketConnection;
  private MockHttpRequestParser requestParser;
  private MockHttpRouter router;
  private MockHttpServerIO serverIO;
  private HttpServerRunner server;
  
  protected void setUp() {
    OutputStream outputStream = new ByteArrayOutputStream();
    InputStream inputStream = new ByteArrayInputStream("anything".getBytes());
    socketConnection = new MockHttpSocketConnection(inputStream, outputStream);
    requestParser = new MockHttpRequestParser();
    router = new MockHttpRouter();
    serverIO = new MockHttpServerIO();
    server = new HttpServerRunner(socketConnection, requestParser, router, serverIO);
    server.run();
  }

  protected void tearDown() {
    server = null;
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