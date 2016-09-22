package carpentern.server;

import carpentern.handler.Handler;
import carpentern.io.ServerOutput;
import carpentern.request.HttpRequest;
import carpentern.request.RequestParser;
import carpentern.response.HttpResponse;
import carpentern.router.Router;
import carpentern.socket.SocketConnection;
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