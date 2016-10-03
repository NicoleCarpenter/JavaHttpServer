import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.request.RequestParser;
import carpentern.coreServer.socket.SocketConnection;
import java.util.HashMap;
import java.io.IOException;

class MockHttpRequestParser implements RequestParser {
  boolean parseRequestCalled = false;

  public HttpRequest parseRequest(SocketConnection socket) throws IOException {
    parseRequestCalled = true;
    return new HttpRequest("mockMethod", "mockUri", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "mockBody");
  }

}