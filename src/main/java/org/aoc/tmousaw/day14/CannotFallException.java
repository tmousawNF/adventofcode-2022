package org.aoc.tmousaw.day14;

public class CannotFallException extends Exception {
  public CannotFallException(Sand sand) {
    super("Grain of sand cannot fall. pos=" + sand.toString());
  }
}
