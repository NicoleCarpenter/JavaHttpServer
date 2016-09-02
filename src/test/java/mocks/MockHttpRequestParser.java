import request.HttpRequest;
import request.RequestParser;
import socket.SocketConnection;
import java.util.HashMap;
import java.io.IOException;

public class MockHttpRequestParser implements RequestParser {
  private boolean parseRequestCalled = false;

  public HttpRequest parseRequest(SocketConnection socket) throws IOException {
    parseRequestCalled = true;
    HttpRequest mockRequest = new HttpRequest("mockMethod", "mockUri", "mockParams", "mockHttpVersion", new HashMap<String, String>(), "mockBody");
    return mockRequest;
  }

  public boolean parseRequestCalled() {
    return parseRequestCalled;
  }
}