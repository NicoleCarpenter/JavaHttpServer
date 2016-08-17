package com.carpentern;

import java.io.IOException;

public class HttpServer {
  ServerSocketInterface serverSocket;
  SocketConnection socket;
  RequestParser requestParser;
  Handler handler;
  ServerIO serverIO;
  HttpRequest request;
  HttpResponse response;

  public HttpServer(ServerSocketInterface serverSocket, RequestParser requestParser, Handler handler, ServerIO serverIO) {
    this.serverSocket = serverSocket;
    this.requestParser = requestParser;
    this.handler = handler;
    this.serverIO = serverIO;
  }

  public void run() {
    try {
      socket = serverSocket.listen();
      request = requestParser.parseRequest(socket);
      response = handler.handleRoute(request);
      serverIO.writeResponse(response.formatToBytes(), socket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}