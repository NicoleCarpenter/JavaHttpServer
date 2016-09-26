package carpentern.coreServer.httpServer.request;

import carpentern.coreServer.httpServer.socket.SocketConnection;
import java.io.IOException;

public interface RequestParser {
  public abstract HttpRequest parseRequest(SocketConnection socket) throws IOException;
}