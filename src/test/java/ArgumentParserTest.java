import com.carpentern.*;

public class ArgumentParserTest extends junit.framework.TestCase {

  public void testGetPortGiven() {
    String[] args = new String[] {"3333"};
    ArgumentParser argParser = new ArgumentParser(args);
    assertEquals((Integer)3333, argParser.getPort());
  }

  public void testGetPortDefault() {
    String[] args = new String[] {};
    ArgumentParser argParser = new ArgumentParser(args);
    assertEquals((Integer)5000, argParser.getPort());
  }
}