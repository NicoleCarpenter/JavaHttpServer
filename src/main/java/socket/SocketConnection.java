package com.carpentern;

import java.io.InputStream;
import java.io.OutputStream;

public interface SocketConnection {
  public InputStream getInputStream();
  public OutputStream getOutputStream();
}