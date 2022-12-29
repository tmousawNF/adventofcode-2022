package org.aoc.tmousaw.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class BoilingBouldersTest {

  @Test
  void testBoilingBoulders() throws IOException {
    BoilingBoulders boilingBoulders = new BoilingBoulders();
    boilingBoulders.solve();

    assertEquals("3466", boilingBoulders.getAnswers().get(0).getValue());
    assertEquals("2012", boilingBoulders.getAnswers().get(1).getValue());
  }

  @Test
  void testBoilingBouldersSample() throws IOException {
    BoilingBoulders boilingBoulders = new BoilingBoulders("sample.txt");
    boilingBoulders.solve();

    assertEquals("64", boilingBoulders.getAnswers().get(0).getValue());
    assertEquals("58", boilingBoulders.getAnswers().get(1).getValue());
  }
}
