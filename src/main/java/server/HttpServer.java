package com.carpentern;

import java.io.IOException;

public class HttpServer extends Thread {
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

  public void start() {

    try {
      while(!serverSocket.isConnectionClosed()) {
        socketConnection = serverSocket.listen();
        try {
          HttpServerRunner connection = new HttpServerRunner(socketConnection, requestParser, router, serverIO);
          Thread thread = new Thread(connection);
          thread.start();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}