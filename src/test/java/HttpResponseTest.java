import com.carpentern.*;

import java.util.HashMap;

public class HttpResponseTest extends junit.framework.TestCase {
  HttpResponse response;

  protected void setUp() {
    response = new HttpResponse();
  }

  protected void tearDown() {
    response = null;
  }

  public void testSetHeader() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Key", "Value");
    response.setHeader("Key", "Value");
    assertEquals(testHeaders, response.getHeaderLines());
  }

  public void testNoneHeadersToString() {
    assertEquals("", response.headersToString());
  }

  public void testOneHeadersToString() {
    response.setHeader("Content-Type", "text/html");
    assertEquals("Content-Type: text/html\r\n", response.headersToString());
  }

  public void testMultipleHeadersToString() {
    response.setHeader("Last-Modified", "Tue, 15 Nov 1994 12:45:26 GMT");
    response.setHeader("Content-Length", "9");
    response.setHeader("Content-Type", "text/html");
    assertEquals("Last-Modified: Tue, 15 Nov 1994 12:45:26 GMT\r\nContent-Length: 9\r\nContent-Type: text/html\r\n", response.headersToString());
  }

  public void testBodyToString() {
    String responseBody = "Body to string";
    MockHttpFileIO fileIO = new MockHttpFileIO();
    fileIO.stubResponseBody(responseBody);

    HttpResponseBuilder builder = new HttpResponseBuilder(fileIO);
    builder.setBody("/index.txt");
    HttpResponse response = builder.getResponse();
    assertEquals(responseBody, response.bodyToString());
  }
  
}