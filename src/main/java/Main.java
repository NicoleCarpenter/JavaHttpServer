package com.carpentern;

import java.net.ServerSocket;
import java.io.IOException;

public class Main {

  public static void main(String args[]) {
    Integer port = 5000;
    ServerSocket serverSocket;

    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    HttpServerSocket httpServerSocket = new HttpServerSocket(serverSocket);
    HttpRequestParser httpRequestParser = new HttpRequestParser();
    HttpHandler httpHandler = new HttpHandler();
    HttpServerIO httpServerIO = new HttpServerIO();
    
    HttpServer server = new HttpServer(httpServerSocket, httpRequestParser, httpHandler, httpServerIO);

    server.run();
  }
}