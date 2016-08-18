import com.carpentern.*;

import java.util.HashMap;

public class FileHandlerTest extends junit.framework.TestCase {
  Response response;
  String responseBody;
  MockHttpFileIO fileIO;
  MockHttpFileSystem fileSystem;
  HttpResponseBuilder responseBuilder;

  public void testHandleRouteIsFile() {
    responseBody = "This is a file";
    fileIO = new MockHttpFileIO();
    fileIO.stubResponseBody(responseBody);
    responseBuilder = new HttpResponseBuilder(fileIO);

    fileSystem = new MockHttpFileSystem();
    fileSystem.stubIsFile(true);

    Handler handler = new FileHandler(responseBuilder, fileSystem);
    HttpRequest request = new HttpRequest("GET", "/file", "HTTP/1.1", new HashMap<String, String>(), "");    
    
    response = handler.handleRoute(request);
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertEquals("HTTP/1.1", response.getHttpVersion());
    assertEquals("200", response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals(testHeaders, response.getHeaderLines());
    assertEquals(responseBody, response.getBody());
  }

  public void testHandleRouteIsDirectory() {
    assertTrue(true);
  }
}