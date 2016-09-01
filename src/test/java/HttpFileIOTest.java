import com.carpentern.*;

import java.io.File;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class HttpFileIOTest extends junit.framework.TestCase {
  private String root;
  private HttpFileIO fileIO;

  protected void setUp() {
    root = "/Users/foo/Desktop/coding/java/applications/JavaHttpServer/src/test/java/testFiles";
    fileIO = new HttpFileIO(new File(root));
  }

  protected void tearDown() {
    fileIO.deleteFileContent(root + "/test-file-2.txt");
  }

  public void testGetFileContents() {
    String filePath = root + "/test-file-1.txt";
    assertEquals("Some text", new String(fileIO.getFileContents(filePath)));
  }

  public void testWriteToFile() {
    String filePath = root + "/test-file-2.txt";
    String content = "Hello World";
    fileIO.writeToFile(filePath, content);
    assertEquals("Hello World\n", new String(fileIO.getFileContents(filePath)));
  }
}