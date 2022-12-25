package org.aoc.tmousaw.day5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class SupplyStacksTest {

  @Test
  public void testSupplyStacks() throws IOException {
    SupplyStacks supplyStacks = new SupplyStacks();
    supplyStacks.solve();

    assertEquals("VQZNJMWTR", supplyStacks.getAnswers().get(0).getValue());
    assertEquals("NLCDCLVMQ", supplyStacks.getAnswers().get(1).getValue());
  }
}
