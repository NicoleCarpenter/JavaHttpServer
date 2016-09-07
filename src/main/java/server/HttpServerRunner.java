package server;

import handler.Handler;
import io.ServerIO;
import request.HttpRequest;
import request.RequestParser;
import response.Response;
import router.Router;
import socket.SocketConnection;
import java.io.IOException;

public class HttpServerRunner implements Runnable {
  SocketConnection socketConnection;
  RequestParser requestParser;
  Router router;
  ServerIO serverIO;

  public HttpServerRunner(SocketConnection socketConnection, RequestParser requestParser, Router router, ServerIO serverIO) {
    this.socketConnection = socketConnection;
    this.requestParser = requestParser;
    this.router = router;
    this.serverIO = serverIO;
  }

  public void run() {
    try {
      HttpRequest request = requestParser.parseRequest(socketConnection);
      Handler handler = router.getRoute(request);
      Response response = handler.handleRoute(request);
      serverIO.writeResponse(response.getFormattedResponse(), socketConnection.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}