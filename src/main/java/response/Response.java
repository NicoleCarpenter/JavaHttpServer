package com.carpentern;

import java.util.HashMap;

public interface Response {
  public abstract byte[] formatToBytes();
  public abstract String getHttpVersion();
  public abstract String getStatusCode();
  public abstract String getStatusMessage();
  public abstract HashMap<String, String> getHeaderLines();
  public abstract String getBody();
  public abstract String formatToString();
}