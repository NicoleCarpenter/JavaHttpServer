package com.carpentern;

import java.io.IOException;

public interface RequestParser {
  public abstract HttpRequest parseRequest(SocketConnection socket) throws IOException;
}