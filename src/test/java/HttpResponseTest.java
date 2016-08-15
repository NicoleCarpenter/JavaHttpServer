import com.carpentern.*;

import java.util.HashMap;

public class HttpResponseTest extends junit.framework.TestCase {

  public void testNoneHeadersToString() {
    HashMap<String, String> headers = new HashMap<String, String>();
    HttpResponse response = new HttpResponse("HTTP/1.0", "200", "OK", headers, "Hello World");
    assertEquals("", response.headersToString());
  }

  public void testOneHeadersToString() {
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "text/html");

    HttpResponse response = new HttpResponse("HTTP/1.0", "200", "OK", headers, "Hello World");
    System.out.println(response.getHeaderLines());
    assertEquals("Content-Type: text/html\r\n", response.headersToString());
  }

  public void testMultipleHeadersToString() {
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Last-Modified", "Tue, 15 Nov 1994 12:45:26 GMT");
    headers.put("Content-Length", "9");
    headers.put("Content-Type", "text/html");

    HttpResponse response = new HttpResponse("HTTP/1.0", "200", "OK", headers, "Hello World");
    System.out.println(response.getHeaderLines());
    assertEquals("Last-Modified: Tue, 15 Nov 1994 12:45:26 GMT\r\nContent-Length: 9\r\nContent-Type: text/html\r\n", response.headersToString());
  }
}