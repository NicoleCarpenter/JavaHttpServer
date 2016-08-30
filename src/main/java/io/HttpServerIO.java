package com.carpentern;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HttpServerIO implements ServerIO {

  @Override
  public String readRequest(InputStream input) throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(input);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    StringBuilder rawRequest = new StringBuilder();
    String line = bufferedReader.readLine();

    while (!line.isEmpty()) {
      rawRequest.append(line);
      rawRequest.append("\n");
      line = bufferedReader.readLine();
    }
    return rawRequest.toString();
  }

  public void writeResponse(byte[] response, OutputStream output) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(output);
    dataOutputStream.write(response, 0, response.length);
    dataOutputStream.flush();
    dataOutputStream.close();
  }
}