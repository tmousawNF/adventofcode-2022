package org.aoc.tmousaw.testris;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.aoc.tmousaw.tetris.Point;
import org.aoc.tmousaw.tetris.Rock;
import org.junit.jupiter.api.Test;

public class RockTest {
  @Test
  public void testRock() {
    Rock rock = Rock.horizontalLine();
    rock.moveRight();

    for (int i = 4; i <= 7; i++) {
      Point p = new Point(i, 4);
      assertTrue(rock.getPoints().contains(p), "Expected true but got false at i=" + i);
    }
  }
}
