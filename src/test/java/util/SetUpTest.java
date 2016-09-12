
import handler.*;
import router.Router;
import response.HttpResponseBuilder;
import file.FileSystem;
import io.FileIO;
import util.FileTypeMatcher;
import util.SetUp;

public class SetUpTest extends junit.framework.TestCase {
  private MockHttpRouter router;
  private HttpResponseBuilder responseBuilder;
  private MockHttpFileSystem fileSystem;
  private MockHttpFileIO fileIO;
  private FileTypeMatcher typeMatcher;
  private SetUp setUp;

  protected void setUp() {
    router = new MockHttpRouter();
    responseBuilder = new HttpResponseBuilder();
    fileSystem = new MockHttpFileSystem();
    fileIO = new MockHttpFileIO();
    typeMatcher = new FileTypeMatcher();
    setUp = new SetUp();
  }

  public void testRegisterRoutes() {
    setUp.registerRoutes(router, responseBuilder, fileSystem, fileIO);
    assertTrue(router.registerRouteCalled);
  }

  public void testRegisterMethodHandlers() {
    setUp.registerMethodHandlers(router, responseBuilder, fileSystem, fileIO, typeMatcher);
    assertTrue(router.registerMethodHandlerCalled);
  }

}