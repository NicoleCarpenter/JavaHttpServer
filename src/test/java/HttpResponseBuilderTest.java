import com.carpentern.*;

import java.util.HashMap;

public class HttpResponseBuilderTest extends junit.framework.TestCase {
  HttpResponseBuilder builder;
  HttpResponse response;
  MockHttpFileIO fileIO;

  protected void setUp() {
    fileIO = new MockHttpFileIO();
    builder = new HttpResponseBuilder(fileIO);
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
    fileIO.stubResponseBody(responseBody);
    builder.setBody("/");
    response = builder.getResponse();
    assertEquals(responseBody, response.getBody());
  }

  public void testSetBodyEmpty() {
    builder.setBodyEmpty();
    response = builder.getResponse();
    assertEquals("", response.getBody());
  }

  public void testSetBodyMessage() {
    String responseMessage = "This is a body message";
    builder.setBodyMessage(responseMessage);
    response = builder.getResponse();
    assertEquals(responseMessage, response.getBody());
  }

  public void testGetResponse() {
    builder.setStatusCode("200");
    builder.setStatusMessage("OK");
    builder.setDefaultHeaders();
    builder.setBodyEmpty();
    HttpResponse response = builder.getResponse();

    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals("", response.getBody());
  }

}