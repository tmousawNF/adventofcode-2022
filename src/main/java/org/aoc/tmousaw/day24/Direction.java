package org.aoc.tmousaw.day24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Direction {
  NORTH('^'),
  SOUTH('v'),
  EAST ('>'),
  WEST('<');

  private final char dir;
  private static final Map<Character, Direction> charToDirectionMap;
  static {
    charToDirectionMap = new HashMap<>();
    Arrays.stream(Direction.values()).forEach(d -> charToDirectionMap.put(d.dir, d));
  }

  Direction(char dir) {
    this.dir = dir;
  }

  public static Direction valueOf(char c) {
    if (!charToDirectionMap.containsKey(c)) {
      throw new IllegalArgumentException("Unknown direction character=" + c);
    }
    return charToDirectionMap.get(c);
  }

  public char getDir() {
    return dir;
  }
}
