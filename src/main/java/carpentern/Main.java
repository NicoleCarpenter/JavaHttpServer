package carpentern;

import carpentern.file.HttpFileSystem;
import carpentern.io.HttpServerOutput;
import carpentern.io.HttpFileIO;
import carpentern.request.HttpRequestParser;
import carpentern.response.HttpResponseBuilder;
import carpentern.router.HttpRouter;
import carpentern.parser.HttpParamParser;
import carpentern.server.HttpServer;
import carpentern.socket.HttpServerSocket;
import carpentern.util.ArgumentParser;
import carpentern.util.FileTypeMatcher;
import carpentern.util.SetUp;

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
    HttpServerOutput httpServerOutput = new HttpServerOutput();
    HttpParamParser httpParamParser = new HttpParamParser();
    HttpRequestParser httpRequestParser = new HttpRequestParser(httpServerOutput, httpParamParser);
    File rootDirectory = new File(argsParser.getRootDirectory());
    HttpFileSystem fileSystem = new HttpFileSystem();
    HttpFileIO fileIO = new HttpFileIO(rootDirectory, fileSystem);
    HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
    FileTypeMatcher typeMatcher = new FileTypeMatcher();

    SetUp setUp = new SetUp();
    setUp.registerRoutes(httpResponseBuilder, fileSystem, fileIO, typeMatcher);
    HttpRouter httpRouter = new HttpRouter(rootDirectory, fileSystem, fileIO, httpResponseBuilder, typeMatcher);

    HttpServer server = new HttpServer(httpServerSocket, httpRequestParser, httpRouter, httpServerOutput);

    server.start();
  }
}
