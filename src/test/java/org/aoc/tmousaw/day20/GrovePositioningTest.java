package org.aoc.tmousaw.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class GrovePositioningTest {
  @Test
  public void testGrovePositioning() throws IOException {
    GrovePositioning grovePositioning = new GrovePositioning();
    grovePositioning.solve();

    assertEquals("11037", grovePositioning.getAnswers().get(0).getValue());
    assertEquals("3033720253914", grovePositioning.getAnswers().get(0).getValue());
  }

  @Test
  public void testGrovePositioningSample() throws IOException {
    GrovePositioning grovePositioning = new GrovePositioning("sample.txt");
    grovePositioning.solve();

    assertEquals("3", grovePositioning.getAnswers().get(0).getValue());
    assertEquals("1623178306", grovePositioning.getAnswers().get(1).getValue());
  }
}
