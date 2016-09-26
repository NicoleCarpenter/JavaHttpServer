package carpentern.coreServer.httpServer.server;

import carpentern.coreServer.httpServer.handler.Handler;
import carpentern.coreServer.httpServer.io.ServerOutput;
import carpentern.coreServer.httpServer.request.HttpRequest;
import carpentern.coreServer.httpServer.request.RequestParser;
import carpentern.coreServer.httpServer.response.HttpResponse;
import carpentern.coreServer.httpServer.router.Router;
import carpentern.coreServer.httpServer.socket.SocketConnection;
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