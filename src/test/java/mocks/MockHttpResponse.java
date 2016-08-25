package com.carpentern;

import java.util.HashMap;

public class MockHttpResponse implements Response {
  boolean formatToBytesCalled = false;
  boolean getHttpVersionCalled = false;
  boolean getStatusCodeCalled = false;
  boolean getStatusMessageCalled = false;
  boolean getHeaderLinesCalled = false;
  boolean getBodyCalled = false;

  public byte[] formatToBytes() {
    formatToBytesCalled = true;
    return "Mock Response".getBytes();
  }

  public boolean formatToBytesCalled() {
    return formatToBytesCalled;
  }

  public String getHttpVersion() {
    getHttpVersionCalled = true;
    return "Http Version";
  }

  public boolean getHttpVersionCalled() {
    return getHttpVersionCalled;
  }

  public String getStatusCode() {
    getStatusCodeCalled = true;
    return "Status Code";
  }

  public boolean getStatusCodeCalled() {
    return getStatusCodeCalled;
  }

  public String getStatusMessage() {
    getStatusMessageCalled = true;
    return "Status Message";
  }

  public boolean getStatusMessageCalled() {
    return getStatusMessageCalled;
  }

  public HashMap<String, String> getHeaderLines() {
    getHeaderLinesCalled = true;
    return new HashMap<String, String>();
  }

  public boolean getHeaderLinesCalled() {
    return getHeaderLinesCalled;
  }

  public String getBody() {
    getBodyCalled = true;
    return "Get Body";
  }

  public boolean getBodyCalled() {
    return getBodyCalled;
  }

  public String formatToString() {
    return "Hello world";
  }
}