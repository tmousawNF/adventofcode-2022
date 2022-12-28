package org.aoc.tmousaw.day24;

import java.util.Objects;
import org.aoc.tmousaw.geometry.Point;

public class Blizzard extends Point {
  private final Direction direction;

  public Blizzard(int x, int y, char dir) {
    super(x, y);
    direction = Direction.valueOf(dir);
  }

  public Direction getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Point)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Blizzard blizzard = (Blizzard) o;
    return direction == blizzard.direction;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), direction);
  }
}
