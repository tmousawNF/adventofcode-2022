package org.aoc.tmousaw.cave;

public class IntoTheAbyssException extends Exception {
  public IntoTheAbyssException(Sand sand) {
    super("The grain of sand falls into the abyss starting at " + sand.toString());
  }
}
