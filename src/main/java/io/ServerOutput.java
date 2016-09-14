package io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public interface ServerOutput {
  public abstract void writeResponse(byte[] response, OutputStream output) throws IOException;
}