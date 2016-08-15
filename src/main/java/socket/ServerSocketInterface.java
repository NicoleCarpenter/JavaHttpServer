package com.carpentern;

import java.io.IOException;

public interface ServerSocketInterface {
  public SocketConnection listen() throws IOException;
}