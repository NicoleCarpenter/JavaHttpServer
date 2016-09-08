package socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class HttpServerSocket implements ServerSocketInterface {
  private ServerSocket serverSocket;

  public HttpServerSocket(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  @Override
  public SocketConnection listen() throws IOException {
    Socket socket = serverSocket.accept();
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    return new HttpSocketConnection(socket, inputStream, outputStream);
  }

  @Override
  public boolean isConnectionClosed() throws IOException {
    return serverSocket.isClosed();
  }
}
