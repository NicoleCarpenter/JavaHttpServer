package com.carpentern;

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
      serverIO.writeResponse(response.formatToBytes(), socketConnection.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}