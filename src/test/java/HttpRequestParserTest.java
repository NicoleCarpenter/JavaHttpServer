import com.carpentern.*;

import java.util.HashMap;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpRequestParserTest extends junit.framework.TestCase {
  MockHttpSocketConnection socketConnection;
  RequestParser requestParser;

  protected void setUp() {
    requestParser = new HttpRequestParser();
  }

  private HttpRequest createTestParsedRequest(String request) throws IOException {
    OutputStream outputStream = new ByteArrayOutputStream();
    InputStream inputStream = new ByteArrayInputStream(request.getBytes());
    socketConnection = new MockHttpSocketConnection(inputStream, outputStream);
    return requestParser.parseRequest(socketConnection);
  }

  public void testGetInputStreamCalled() throws IOException {
    String request = "GET / HTTP/1.0\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertTrue(socketConnection.getInputStreamCalled());
  }


  public void testParseRequest() throws IOException {
    String request = "GET / HTTP/1.0\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals("GET", parsedRequest.getMethod());
    assertEquals("/", parsedRequest.getUri());
    assertEquals("HTTP/1.0", parsedRequest.getHttpVersion());
    assertEquals(new HashMap<>(), parsedRequest.getHeaderLines());
    assertEquals("", parsedRequest.getBody());
  }

  public void testParseRequestWithBody() throws IOException {
    String request = "GET /index.html HTTP/1.0\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    assertEquals("GET", parsedRequest.getMethod());
    assertEquals("/index.html", parsedRequest.getUri());
    assertEquals("HTTP/1.0", parsedRequest.getHttpVersion());
    assertEquals(new HashMap<>(), parsedRequest.getHeaderLines());
    assertEquals("Hello World", parsedRequest.getBody());
  }

  public void testParseRequestSingleHeader() throws IOException {
    String request = "POST /index.html HTTP/1.0\r\nAccept: text/plain\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");

    assertEquals("POST", parsedRequest.getMethod());
    assertEquals("/index.html", parsedRequest.getUri());
    assertEquals("HTTP/1.0", parsedRequest.getHttpVersion());
    assertEquals(headers, parsedRequest.getHeaderLines());
    assertEquals("", parsedRequest.getBody());
  }

  public void testParseRequestMultipleHeaders() throws IOException {
    String request = "GET /index.html HTTP/1.0\r\nAccept: text/plain\r\nAccept-Language: en-US\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");
    headers.put("Accept-Language", "en-US");

    assertEquals("GET", parsedRequest.getMethod());
    assertEquals("/index.html", parsedRequest.getUri());
    assertEquals("HTTP/1.0", parsedRequest.getHttpVersion());
    assertEquals(headers, parsedRequest.getHeaderLines());
    assertEquals("", parsedRequest.getBody());
  }

  public void testParseRequestSingleHeaderWithBody() throws IOException {
    String request = "POST /index.html HTTP/1.0\r\nAccept: text/plain\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");

    assertEquals("POST", parsedRequest.getMethod());
    assertEquals("/index.html", parsedRequest.getUri());
    assertEquals("HTTP/1.0", parsedRequest.getHttpVersion());
    assertEquals(headers, parsedRequest.getHeaderLines());
    assertEquals("Hello World", parsedRequest.getBody());
  }

  public void testParseRequestMultipleHeadersWithBody() throws IOException {
    String request = "POST /index.html HTTP/1.0\r\nAccept: text/plain\r\nAccept-Language: en-US\r\n\r\nHello World\r\n\r\n";
    HttpRequest parsedRequest = createTestParsedRequest(request);

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", "text/plain");
    headers.put("Accept-Language", "en-US");

    assertEquals("POST", parsedRequest.getMethod());
    assertEquals("/index.html", parsedRequest.getUri());
    assertEquals("HTTP/1.0", parsedRequest.getHttpVersion());
    assertEquals(headers, parsedRequest.getHeaderLines());
    assertEquals("Hello World", parsedRequest.getBody());
  }
}