package carpentern.coreServer.server;

import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.io.ServerOutput;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.request.RequestParser;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.router.Router;
import carpentern.coreServer.socket.SocketConnection;
import java.io.IOException;

public class HttpServerRunner implements Runnable {
  SocketConnection socketConnection;
  RequestParser requestParser;
  Router router;
  ServerOutput serverIO;

  public HttpServerRunner(SocketConnection socketConnection, RequestParser requestParser, Router router, ServerOutput serverIO) {
    this.socketConnection = socketConnection;
    this.requestParser = requestParser;
    this.router = router;
    this.serverIO = serverIO;
  }

  public void run() {
    try {
      HttpRequest request = requestParser.parseRequest(socketConnection);
      Handler handler = router.getRoute(request);
      HttpResponse response = handler.handleRoute(request);
      serverIO.writeResponse(response.getFormattedResponse(), socketConnection.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}