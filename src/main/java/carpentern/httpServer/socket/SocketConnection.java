package carpentern.coreServer.httpServer.socket;

import java.io.InputStream;
import java.io.OutputStream;

public interface SocketConnection {
  public abstract InputStream getInputStream();
  public abstract OutputStream getOutputStream();
}