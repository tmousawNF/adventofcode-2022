package org.aoc.tmousaw.day9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class RopeBridgeTest {

  @Test
  void testRopeBridge() throws IOException {
    RopeBridge ropeBridge = new RopeBridge();
    ropeBridge.solve();
    assertEquals("6498", ropeBridge.getAnswers().get(0).getValue());
    assertEquals("2531", ropeBridge.getAnswers().get(1).getValue());
  }
}
