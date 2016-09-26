package carpentern.coreServer.httpServer;

import carpentern.coreServer.httpServer.file.HttpFileSystem;
import carpentern.coreServer.httpServer.io.HttpServerOutput;
import carpentern.coreServer.httpServer.io.HttpFileIO;
import carpentern.coreServer.httpServer.request.HttpRequestParser;
import carpentern.coreServer.httpServer.response.HttpResponseBuilder;
import carpentern.coreServer.httpServer.router.HttpRouter;
import carpentern.coreServer.httpServer.parser.HttpParamParser;
import carpentern.coreServer.httpServer.server.HttpServer;
import carpentern.coreServer.httpServer.socket.HttpServerSocket;
import carpentern.coreServer.httpServer.util.ArgumentParser;
import carpentern.coreServer.httpServer.util.FileTypeMatcher;
import carpentern.coreServer.httpServer.util.SetUp;

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
