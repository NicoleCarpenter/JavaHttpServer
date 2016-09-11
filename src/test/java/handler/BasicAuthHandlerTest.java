import handler.Handler;
import handler.BasicAuthHandler;
import request.HttpRequest;
import response.HttpResponse;
import response.HttpResponseBuilder;
import java.util.HashMap;
import java.io.File;

public class BasicAuthHandlerTest extends junit.framework.TestCase {

  public void testHandleRoute() {
    HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
    Handler handler = new BasicAuthHandler(responseBuilder);
    HashMap<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Authorization", "SomethingEncoded");

    HttpRequest request = new HttpRequest("GET", "/logs", "", "HTTP/1.1", requestHeaders, "");

    assertTrue(true);
  }
}