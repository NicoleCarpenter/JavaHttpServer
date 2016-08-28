package com.carpentern;

import java.io.IOException;

public class HttpServer {
  private ServerSocketInterface serverSocket;
  private SocketConnection socketConnection;
  private RequestParser requestParser;
  private Router router;
  private ServerIO serverIO;

  public HttpServer(ServerSocketInterface serverSocket, RequestParser requestParser, Router router, ServerIO serverIO) {
    this.serverSocket = serverSocket;
    this.requestParser = requestParser;
    this.router = router;
    this.serverIO = serverIO;
  }

  public void run() {

    try {
      while(true) {
        socketConnection = serverSocket.listen();
        HttpRequest request = requestParser.parseRequest(socketConnection);
        Handler handler = router.getRoute(request);
        Response response = handler.handleRoute(request);        
        serverIO.writeResponse(response.formatToBytes(), socketConnection.getOutputStream());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}