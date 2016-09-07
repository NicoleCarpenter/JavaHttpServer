import response.Response;
import java.util.HashMap;
import java.io.IOException;

public class MockHttpResponse implements Response {
  private boolean getHttpVersionCalled = false;
  private boolean getStatusCodeCalled = false;
  private boolean getStatusMessageCalled = false;
  private boolean getHeaderLinesCalled = false;
  private boolean getBodyCalled = false;
  private boolean getFormattedResponseCalled = false;

  public String getHttpVersion() {
    getHttpVersionCalled = true;
    return "Http Version";
  }

  public boolean getHttpVersionCalled() {
    return getHttpVersionCalled;
  }

  public String getStatusCode() {
    getStatusCodeCalled = true;
    return "Status Code";
  }

  public boolean getStatusCodeCalled() {
    return getStatusCodeCalled;
  }

  public String getStatusMessage() {
    getStatusMessageCalled = true;
    return "Status Message";
  }

  public boolean getStatusMessageCalled() {
    return getStatusMessageCalled;
  }

  public HashMap<String, String> getHeaderLines() {
    getHeaderLinesCalled = true;
    return new HashMap<String, String>();
  }

  public boolean getHeaderLinesCalled() {
    return getHeaderLinesCalled;
  }

  public byte[] getBody() {
    getBodyCalled = true;
    return "Get Body".getBytes();
  }

  public boolean getBodyCalled() {
    return getBodyCalled;
  }

  public byte[] getFormattedResponse() throws IOException {
    getFormattedResponseCalled = true;
    return "Mock Response".getBytes();
  }

  public boolean getFormattedResponseCalled() {
    return getFormattedResponseCalled;
  }

}