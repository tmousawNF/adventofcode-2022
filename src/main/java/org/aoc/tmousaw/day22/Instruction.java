package org.aoc.tmousaw.day22;

public class Instruction {
  public final String value;

  public Instruction(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
