package org.aoc.tmousaw.cave;

public class CannotFallException extends Exception {
  public CannotFallException(Sand sand) {
    super("Grain of sand cannot fall. pos=" + sand.toString());
  }
}
