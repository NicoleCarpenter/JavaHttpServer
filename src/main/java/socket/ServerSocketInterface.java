package com.carpentern;

import java.io.IOException;

public interface ServerSocketInterface {
  public abstract SocketConnection listen() throws IOException;
  public abstract boolean isConnectionClosed() throws IOException;
}