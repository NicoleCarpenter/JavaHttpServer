package com.carpentern;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.File;

public class Main {

  public static void main(String args[]) {
    ArgumentParser argsParser = new ArgumentParser(args);
    Integer port = argsParser.getPort();
    ServerSocket serverSocket;

    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    HttpServerSocket httpServerSocket = new HttpServerSocket(serverSocket);
    HttpServerIO httpServerIO = new HttpServerIO();
    HttpRequestParser httpRequestParser = new HttpRequestParser(httpServerIO);
    File rootDirectory = new File(argsParser.getRootDirectory());
    HttpFileIO httpFileIO = new HttpFileIO(rootDirectory);
    HttpFileSystem fileSystem = new HttpFileSystem();
    HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder(httpFileIO);
    HttpRouter httpRouter = new HttpRouter(rootDirectory, fileSystem, httpResponseBuilder);
    
    HttpServer server = new HttpServer(httpServerSocket, httpRequestParser, httpRouter, httpServerIO);

    server.start();
  }
}