import com.carpentern.*;

import java.util.HashMap;

public class MockHttpRequestParser implements RequestParser {
  boolean parseRequestCalled = false;

  public HttpRequest parseRequest(SocketConnection socket) {
    parseRequestCalled = true;
    HttpRequest mockRequest = new HttpRequest("mockMethod", "mockUri", "mockHttpVersion", new HashMap<String, String>(), "mockBody");
    return mockRequest;
  }

  public boolean parseRequestCalled() {
    return parseRequestCalled;
  }
}