package carpentern.coreServer.request;

import carpentern.coreServer.socket.SocketConnection;
import java.io.IOException;

public interface RequestParser {
  HttpRequest parseRequest(SocketConnection socket) throws IOException;
}