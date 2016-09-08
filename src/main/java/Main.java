import file.HttpFileSystem;
import io.HttpServerIO;
import io.HttpFileIO;
import request.HttpRequestParser;
import response.HttpResponseBuilder;
import router.HttpRouter;
import server.HttpServer;
import socket.HttpServerSocket;
import util.ArgumentParser;
import util.SetUp;

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
    HttpFileSystem fileSystem = new HttpFileSystem();
    HttpFileIO fileIO = new HttpFileIO(rootDirectory);
    HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
    HttpRouter httpRouter = new HttpRouter(rootDirectory, fileSystem, fileIO, httpResponseBuilder);
    
    SetUp setUp = new SetUp();
    setUp.setUpRouter(httpRouter, httpResponseBuilder, fileSystem, fileIO);

    HttpServer server = new HttpServer(httpServerSocket, httpRequestParser, httpRouter, httpServerIO);

    server.start();
  }
}