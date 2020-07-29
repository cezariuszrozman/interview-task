package org.ework.parser;


import java.io.IOException;
import java.io.InputStream;

public interface ParseStrategy {

  String getName();

  void execute(InputStream in);

  default String getSentence(InputStream in) throws IOException {
    StringBuilder sb = new StringBuilder();
    int code;
    while ((code = in.read()) != -1) {
      sb.append((char) code);
      if (code == '.') {
        break;
      }
    }
    return sb.toString();
  }
}
