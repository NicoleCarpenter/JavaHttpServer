import com.carpentern.*;

import java.util.HashMap;
import java.io.File;


public class HttpRequestTest extends junit.framework.TestCase {
  private HttpRequest request;

  protected void setUp() {
    request = new HttpRequest("GET", "public/file1.txt", "", "HTTP/1.1", new HashMap<>(), "");
  }

  public void testGetPathFromRoot() {
    assertEquals("/file1.txt", request.getPathFromRoot(new File("/public")));
  }
}