package io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public interface ServerIO {
  public abstract String readRequest(InputStream input) throws IOException;
  public abstract void writeResponse(byte[] response, OutputStream output) throws IOException;
}