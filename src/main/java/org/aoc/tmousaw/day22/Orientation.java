package org.aoc.tmousaw.day22;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Orientation {
  EAST(0), SOUTH(1), WEST(2), NORTH(3);

  private final int index;
  Orientation(int index) {
    this.index = index;
  }

  private static final Map<Integer, Orientation> integerToOrientation;
  static {
    integerToOrientation = new HashMap<>();
    Arrays.stream(Orientation.values()).forEach(o -> integerToOrientation.put(o.index, o));
  }

  public Orientation turn(char direction) {
    int newIndex;
    if (direction == 'L') {
      newIndex = index - 1;
      if (newIndex < 0) {
        newIndex += 4;
      }
    } else if (direction == 'R') {
      newIndex = (index + 1) % 4;
    } else {
      throw new IllegalArgumentException("Unknown direction=" + direction);
    }

    return integerToOrientation.get(newIndex);
  }

  public int getIndex() {
    return index;
  }
//
//  public Orientation valueOf(Integer index) {
//    return integerToOrientation.get(index);
//  }
}
