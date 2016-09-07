import response.Response;
import java.io.IOException;
import java.util.HashMap;

public class Formatter {
  
  public String headersToString(Response response) {
    HashMap<String, String> headerLines = response.getHeaderLines();
    StringBuilder builder = new StringBuilder();
    headerLines.forEach((key, value)-> builder.append(key + ": " + value + "\r\n"));
    return builder.toString();
  }

  public String formatToString(Response response) throws IOException {
    byte[] responseInBytes = response.getFormattedResponse();
    return new String(responseInBytes);
  }

  public String bodyToString(Response response) {
    return new String(response.getBody());
  }
}