package carpentern.coreServer.socket;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

class HttpSocketConnection implements SocketConnection {
  private Socket socket;
  private InputStream inputStream;
  private OutputStream outputStream;

  HttpSocketConnection(Socket socket, InputStream inputStream, OutputStream outputStream) {
    this.socket = socket;
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  public Socket getSocket() {
    return socket;
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }

  @Override
  public OutputStream getOutputStream() {
    return outputStream;
  }
}