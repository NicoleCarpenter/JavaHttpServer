import carpentern.coreServer.parser.HttpParamParser;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.request.HttpRequestParser;
import java.util.HashMap;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpRequestParserTest extends junit.framework.TestCase {
  private MockHttpServerOutput serverIO;
  private MockHttpSocketConnection socketConnection;

  protected void setUp() {
    serverIO = new MockHttpServerOutput();
  }

  private HttpRequest createTestParsedRequest(String request) throws IOException {
    serverIO.stubRequest(request);
    HttpParamParser paramParser = new HttpParamParser();
    HttpRequestParser requestParser = new HttpRequestParser(serverIO, paramParser);
    OutputStream outputStream = new ByteArrayOutputStream();
    InputStream inputStream = new ByteArrayInputStream(request.getBytes());
    socketConnection = new MockHttpSocketConnection(inputStream, outputStream);
    return requestParser.parseRequest(socketConnection);
  }

  public void testGetInputStreamCalled() throws IOException {
    String request = "GET / HTTP/1.1\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertTrue(socketConnection.getInputStreamCalled);
  }

  public void testParseRequestHttpVersion() throws IOException {
    String request = "GET / HTTP/1.1\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals("HTTP/1.1", parsedRequest.getHttpVersion());
  }

  public void testParseRequestMethod() throws IOException {
    String request = "GET / HTTP/1.1\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals("GET", parsedRequest.getMethod());
  }

  public void testParseRequestUri() throws IOException {
    String request = "GET / HTTP/1.1\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals("/", parsedRequest.getUri());
  }

  public void testParseRequestWithParams() throws IOException {
    String request = "GET /index.html?parameter=value HTTP/1.1\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> testParams = new HashMap<>();
    testParams.put("parameter", "value");

    assertEquals(testParams, parsedRequest.getParams());
  }

  public void testParstRequestMultipleParams() throws IOException {
    String request = "GET /index.html?parameter1=value1&parameter2=value2 HTTP/1.1\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> testParams = new HashMap<>();
    testParams.put("parameter1", "value1");
    testParams.put("parameter2", "value2");

    assertEquals(testParams, parsedRequest.getParams());
  }

  public void testParseRequestNoHeaders() throws IOException {
    String request = "GET / HTTP/1.1\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals(new HashMap<>(), parsedRequest.getHeaderLines());
  }

  public void testParseRequestSingleHeader() throws IOException {
    String request = "POST /index.html HTTP/1.1\r\nAccept: text/plain\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");

    assertEquals(headers, parsedRequest.getHeaderLines());
  }

  public void testParseRequestMultipleHeaders() throws IOException {
    String request = "GET /index.html HTTP/1.1\r\nAccept: text/plain\r\nAccept-Language: en-US\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");
    headers.put("Accept-Language", "en-US");

    assertEquals(headers, parsedRequest.getHeaderLines());
  }

  public void testParseRequestSingleHeaderWithBody() throws IOException {
    String request = "POST /index.html HTTP/1.1\r\nAccept: text/plain\r\nContent-Length: 11\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");
    headers.put("Content-Length", "11");

    assertEquals(headers, parsedRequest.getHeaderLines());
    assertEquals("Hello World", parsedRequest.getBody());
  }

  public void testParseRequestMultipleHeadersWithBody() throws IOException {
    String request = "POST /index.html HTTP/1.1\r\nAccept: text/plain\r\nAccept-Language: en-US\r\nContent-Length: 11\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");
    headers.put("Accept-Language", "en-US");
    headers.put("Content-Length", "11");

    assertEquals(headers, parsedRequest.getHeaderLines());
    assertEquals("Hello World", parsedRequest.getBody());
  }

  public void testParseRequestNoBody() throws IOException {
    String request = "GET / HTTP/1.1\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals("", parsedRequest.getBody());
  }

}