package org.aoc.tmousaw.ropebridge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RopeBridgeTest {
  @Test
  public void testSet() {
    Set<Point> positions = new HashSet<>();
    positions.add(new Point());
    positions.add(new Point());

    assertEquals(1, positions.size());
  }
}
