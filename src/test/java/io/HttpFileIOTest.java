import io.HttpFileIO;

import java.io.File;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class HttpFileIOTest extends junit.framework.TestCase {
  private String testRoot;
  private String applicationRoot;
  private HttpFileIO fileIO;

  protected void setUp() {
    testRoot = "/Users/foo/Desktop/coding/java/applications/JavaHttpServer/src/test/java/testFiles";
    applicationRoot = "/Users/foo/Desktop/coding/java/applications/JavaHttpServer/public";
    fileIO = new HttpFileIO(new File(testRoot));
  }

  protected void tearDown() {
    fileIO.deleteFileContent(testRoot + "/test-file-2.txt");
  }

  public void testGetFileContents() {
    String filePath = testRoot + "/test-file-1.txt";
    assertEquals("Some text", new String(fileIO.getFileContents(filePath)));
  }

  public void testGetPartialFileContentsStartToMid() {
    String filePath = applicationRoot + "/partial_content.txt";
    String rawRange = "Range: bytes=0-4";
    assertEquals("This ", new String(fileIO.getPartialFileContents(filePath, rawRange)));
  }

  public void testGetPartialFileContentsEnd() {
    String filePath = applicationRoot + "/partial_content.txt";
    String rawRange = "Range: bytes=-6";
    assertEquals("a 206.", new String(fileIO.getPartialFileContents(filePath, rawRange)));
  }

  public void testGetPartialFileContentsMidToEnd1() {
    String filePath = applicationRoot + "/partial_content.txt";
    String rawRange = "Range: bytes=62-";
    assertEquals("fulfill a 206.",
                 new String(fileIO.getPartialFileContents(filePath, rawRange)));
  }

  public void testGetPartialFileContentsMidToEnd2() {
    String filePath = applicationRoot + "/partial_content.txt";
    String rawRange = "Range: bytes=4-";
    assertEquals(" is a file that contains text to read part of in order to fulfill a 206.",
                 new String(fileIO.getPartialFileContents(filePath, rawRange)));
  }

  public void testGetPartialFileContentsMidToEnd3() {
    String filePath = applicationRoot + "/partial_content.txt";
    String rawRange = "Range: bytes=71-75";
    assertEquals(" 206.",
                 new String(fileIO.getPartialFileContents(filePath, rawRange)));
  }


  public void testWriteToFile() {
    String filePath = testRoot + "/test-file-2.txt";
    String content = "Hello World";
    fileIO.writeToFile(filePath, content);
    assertEquals("Hello World\n", new String(fileIO.getFileContents(filePath)));
  }
}