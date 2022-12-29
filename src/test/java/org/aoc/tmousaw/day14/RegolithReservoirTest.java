package org.aoc.tmousaw.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class RegolithReservoirTest {

  @Test
  void testRegolithReservoir() throws IOException {
    RegolithReservoir regolithReservoir = new RegolithReservoir();
    regolithReservoir.solve();

    assertEquals("1016", regolithReservoir.getAnswers().get(0).getValue());
    assertEquals("25402", regolithReservoir.getAnswers().get(1).getValue());
  }

  @Test
  void testRegolithReservoirSample() throws IOException {
    RegolithReservoir regolithReservoir = new RegolithReservoir("sample.txt");
    regolithReservoir.solve();

    assertEquals("24", regolithReservoir.getAnswers().get(0).getValue());
    assertEquals("93", regolithReservoir.getAnswers().get(1).getValue());
  }
}
