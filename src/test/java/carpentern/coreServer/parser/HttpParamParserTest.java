import carpentern.coreServer.parser.HttpParamParser;
import java.io.IOException;
import java.util.HashMap;

public class HttpParamParserTest extends junit.framework.TestCase {
  private HttpParamParser paramParser;

  protected void setUp() {
    paramParser = new HttpParamParser();
  }

  public void testGetParamsNoParams() throws IOException {
    String rawUri = "/index.html";
    HashMap<String, String> testParams = new HashMap<>();

    assertEquals(testParams, paramParser.getParams(rawUri));
  }

  public void testGetParamsWithParams() throws IOException {
    String rawUri = "/index.html?parameter=value";
    HashMap<String, String> testParams = new HashMap<>();
    testParams.put("parameter", "value");

    assertEquals(testParams, paramParser.getParams(rawUri));
  }

  public void testGetParamsMultipleParams() throws IOException {
    String rawUri = "/index.html?parameter1=value1&parameter2=value2";
    HashMap<String, String> testParams = new HashMap<>();
    testParams.put("parameter1", "value1");
    testParams.put("parameter2", "value2");

    assertEquals(testParams, paramParser.getParams(rawUri));
  }
}