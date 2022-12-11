package org.aoc.tmousaw.ropebridge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RopeBridgeTest {
  @Test
  public void testSet() {
    Set<GridPosition> positions = new HashSet<>();
    positions.add(new GridPosition());
    positions.add(new GridPosition());

    assertEquals(1, positions.size());
  }
}
