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
    InputStream inputStream = new ByteArrayInputStream("GET / HTTP/1.0\r\n\r\n".getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    socketConnection = new MockHttpSocketConnection(inputStream, outputStream);
    requestParser = new HttpRequestParser();
  }

  public void testParseRequest() throws IOException {
    HttpRequest request = requestParser.parseRequest(socketConnection);
    assertEquals("GET", request.getMethod());
    assertEquals("/", request.getUri());
    assertEquals("HTTP/1.0", request.getHttpVersion());
    assertEquals(new HashMap<>(), request.getHeaderLines());
    assertEquals("", request.getBody());
  }
}