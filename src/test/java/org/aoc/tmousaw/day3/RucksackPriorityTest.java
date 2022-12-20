package org.aoc.tmousaw.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class RucksackPriorityTest {

  @Test
  public void testRucksackPriority() throws IOException {
    RucksackPriority rucksackPriority = new RucksackPriority();
    rucksackPriority.solve();
    assertEquals("8401", rucksackPriority.getAnswers().get(0).getValue());
    assertEquals("2641", rucksackPriority.getAnswers().get(1).getValue());
  }
}
