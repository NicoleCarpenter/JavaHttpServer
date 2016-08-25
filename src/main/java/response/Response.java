package com.carpentern;

import java.util.HashMap;

public interface Response {
  public abstract byte[] formatToBytes();
  public String getHttpVersion();
  public String getStatusCode();
  public String getStatusMessage();
  public HashMap<String, String> getHeaderLines();
  public String getBody();
  public String formatToString();
}