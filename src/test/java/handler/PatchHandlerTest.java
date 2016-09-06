import handler.Handler;
import handler.PatchHandler;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import io.FileIO;

public class PatchHandlerTest extends junit.framework.TestCase {
  private Response response;
  private String responseBody;
  private MockHttpFileIO fileIO;
  private MockHttpFileSystem fileSystem;
  private HttpResponseBuilder responseBuilder;

  public void testHandleRoute() {
    responseBody = "default content";
    fileIO = new MockHttpFileIO();
    fileIO.stubResponseBody(responseBody);
    responseBuilder = new HttpResponseBuilder(fileIO);

    String path = "/Users/foo/application/public/patch-content.txt";
    String uri = "/file";
    fileSystem = new MockHttpFileSystem();
    
    assertTrue(true);
  }
}
