package org.aoc.tmousaw.common;

public class Answer {
  private final String message;
  private final String value;
  private final long time;

  public Answer(String message, String value) {
    this.message = message;
    this.value = value;
    time = System.currentTimeMillis();
  }

  public String getMessage() {
    return message;
  }

  public String getValue() {
    return value;
  }

  public long getTime() {
    return time;
  }
}
