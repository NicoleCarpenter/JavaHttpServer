package com.carpentern;

public class ArgumentParser {
  private int port;
  private String[] args;

  public ArgumentParser(String[] args) {
    this.args = args;
  }

  public Integer getPort() {
    try {
      return Integer.parseInt(args[0]);
    } catch (Exception e) {
      return 5000;
    }
  }
}