package carpentern.coreServer.io;

import java.io.OutputStream;
import java.io.IOException;

public interface ServerOutput {
  void writeResponse(byte[] response, OutputStream output) throws IOException;
}