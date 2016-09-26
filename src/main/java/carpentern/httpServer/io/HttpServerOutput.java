package carpentern.coreServer.httpServer.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HttpServerOutput implements ServerOutput {

  @Override
  public void writeResponse(byte[] response, OutputStream output) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(output);
    dataOutputStream.write(response, 0, response.length);
    dataOutputStream.flush();
    dataOutputStream.close();
  }
}