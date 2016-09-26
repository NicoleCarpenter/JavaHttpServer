import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.request.RequestParser;
import carpentern.coreServer.socket.SocketConnection;
import java.util.HashMap;
import java.io.IOException;

public class MockHttpRequestParser implements RequestParser {
  boolean parseRequestCalled = false;

  public HttpRequest parseRequest(SocketConnection socket) throws IOException {
    parseRequestCalled = true;
    HttpRequest mockRequest = new HttpRequest("mockMethod", "mockUri", new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "mockBody");
    return mockRequest;
  }

}