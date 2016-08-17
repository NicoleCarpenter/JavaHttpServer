package com.carpentern;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HttpServerIO implements ServerIO {

  public String readRequest(InputStream input) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
    StringBuilder rawRequest = new StringBuilder();
    int value;

    while ((value = bufferedReader.read()) != -1) {
      char c = (char)value;
      rawRequest.append(c);
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