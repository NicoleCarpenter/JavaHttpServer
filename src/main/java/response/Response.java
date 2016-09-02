package com.carpentern;

import java.util.HashMap;
import java.io.IOException;

public interface Response {
  public abstract String getHttpVersion();
  public abstract String getStatusCode();
  public abstract String getStatusMessage();
  public abstract HashMap<String, String> getHeaderLines();
  public abstract String getBody();
  public abstract byte[] formatToBytes() throws IOException;
  public abstract String formatToString() throws IOException;
}