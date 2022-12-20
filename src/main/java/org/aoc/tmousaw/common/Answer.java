package org.aoc.tmousaw.common;

public class Answer {
  private final String message;
  private final String value;

  public Answer(String message, String value) {
    this.message = message;
    this.value = value;
  }

  public String getMessage() {
    return message;
  }

  public String getValue() {
    return value;
  }
}
