import com.carpentern.*;

import java.util.HashMap;
import java.io.File;

public class FileHandlerTest extends junit.framework.TestCase {
  private Response response;
  private String responseBody;
  private MockHttpFileIO fileIO;
  private MockHttpFileSystem fileSystem;
  private HttpResponseBuilder responseBuilder;

  public void testHandleRouteIsFile() {
    responseBody = "This is a file";
    fileIO = new MockHttpFileIO();
    fileIO.stubResponseBody(responseBody);
    responseBuilder = new HttpResponseBuilder(fileIO);

    String path = "/Users/foo/application/public/file";
    String uri = "/file";
    fileSystem = new MockHttpFileSystem();
    fileSystem.stubIsFile(true);

    Handler handler = new FileHandler(responseBuilder, path, uri, fileSystem);
    HttpRequest request = new HttpRequest("GET", uri, "HTTP/1.1", new HashMap<String, String>(), "");    
    
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