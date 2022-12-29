package org.aoc.tmousaw.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class VolcaniumTest {

  @Test
  @Disabled("not finished yet")
  void testVolcanium() throws IOException {
    Volcanium volcanium = new Volcanium();
    volcanium.solve();

    assertEquals("", volcanium.getAnswers().get(0).getValue());
  }

  @Test
  @Disabled("not finished yet")
  void testVolcaniumSample() throws IOException {
    Volcanium volcanium = new Volcanium("sample.txt");
    volcanium.solve();

    assertEquals("1651", volcanium.getAnswers().get(0).getValue());
  }
}
