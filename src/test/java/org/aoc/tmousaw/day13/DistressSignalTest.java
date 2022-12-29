package org.aoc.tmousaw.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class DistressSignalTest {
  @Test
  void testDistressSignal() throws IOException {
    DistressSignal distressSignal = new DistressSignal();
    distressSignal.solve();

    assertEquals("5506", distressSignal.getAnswers().get(0).getValue());
    assertEquals("21756", distressSignal.getAnswers().get(1).getValue());
  }

  @Test
  void testDistressSignalSample() throws IOException {
    DistressSignal distressSignal = new DistressSignal("sample.txt");
    distressSignal.solve();

    assertEquals("13", distressSignal.getAnswers().get(0).getValue());
    assertEquals("140", distressSignal.getAnswers().get(1).getValue());
  }
}
