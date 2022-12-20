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

  @Test
  void testRopeBridgeTwoKnotSample() throws IOException {
    RopeBridge ropeBridge = new RopeBridge("sample_two_knots.txt");
    ropeBridge.simulate(2);
    assertEquals("13", ropeBridge.getAnswers().get(0).getValue());
  }

  @Test
  void testRopeBridgeTenKnotSample() throws IOException {
    RopeBridge ropeBridge =  new RopeBridge("sample_ten_knots.txt");
    ropeBridge.simulate(10);
    assertEquals("36", ropeBridge.getAnswers().get(0).getValue());
  }
}
