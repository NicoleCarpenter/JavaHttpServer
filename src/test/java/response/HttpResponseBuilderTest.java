import response.HttpResponse;
import response.HttpResponseBuilder;
import java.util.HashMap;

public class HttpResponseBuilderTest extends junit.framework.TestCase {
  private HttpResponseBuilder builder;
  private HttpResponse response;
  private Formatter formatter;

  protected void setUp() {
    formatter = new Formatter();
    builder = new HttpResponseBuilder();
  }

  public void testSetStatusCode() {
    builder.setStatusCode("200");
    response = builder.getResponse();
    assertEquals("200", response.getStatusCode());
  }

  public void testSetStatusMessage() {
    builder.setStatusMessage("OK");
    response = builder.getResponse();
    assertEquals("OK", response.getStatusMessage());
  }

  public void testSetDefaultHeaders() {
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");
    
    builder.setDefaultHeaders();
    response = builder.getResponse();

    assertEquals(testHeaders, response.getHeaderLines());
  }

  public void testSetBody() {
    String responseBody = "This is the root";
    byte[] bodyContent = new String("/").getBytes();
    builder.setBody(bodyContent);
    response = builder.getResponse();
    assertEquals("/", formatter.bodyToString(response));
  }

  public void testSetBodyEmpty() {
    byte[] emptyBody = new String("").getBytes();
    builder.setBody(emptyBody);
    response = builder.getResponse();
    assertEquals("", formatter.bodyToString(response));
  }

  public void testSetBodyMessage() {
    byte[] responseMessage = new String("This is a body message").getBytes();
    builder.setBody(responseMessage);
    response = builder.getResponse();
    assertEquals("This is a body message", formatter.bodyToString(response));
  }

  public void testGetResponse() {
    byte[] emptyBody = new String("").getBytes();
    builder.setStatusCode("200");
    builder.setStatusMessage("OK");
    builder.setDefaultHeaders();
    builder.setBody(emptyBody);
    HttpResponse response = builder.getResponse();

    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", formatter.bodyToString(response));
  }

}