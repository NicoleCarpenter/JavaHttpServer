import response.HttpResponse;
import java.util.HashMap;

public class HttpResponseTest extends junit.framework.TestCase {
  private HttpResponse response;
  private Formatter formatter;

  protected void setUp() {
    formatter = new Formatter();
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
    assertEquals("", formatter.headersToString(response));
  }

  public void testOneHeadersToString() {
    response.setHeader("Content-Type", "text/html");
    assertEquals("Content-Type: text/html\r\n", formatter.headersToString(response));
  }

  public void testMultipleHeadersToString() {
    response.setHeader("Last-Modified", "Tue, 15 Nov 1994 12:45:26 GMT");
    response.setHeader("Content-Length", "9");
    response.setHeader("Content-Type", "text/html");
    assertEquals("Last-Modified: Tue, 15 Nov 1994 12:45:26 GMT\r\nContent-Length: 9\r\nContent-Type: text/html\r\n", formatter.headersToString(response));
  }
  
}