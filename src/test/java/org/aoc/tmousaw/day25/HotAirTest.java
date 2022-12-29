package org.aoc.tmousaw.day25;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class HotAirTest {
  @Test
  public void testHotAir() throws IOException {
    HotAir hotAir = new HotAir();
    hotAir.solve();

    assertEquals("2-2--02=1---1200=0-1", hotAir.getAnswers().get(0).getValue());
  }

  @Test
  public void testHotAirSample() throws IOException {
    HotAir hotAir = new HotAir("sample.txt");
    hotAir.solve();

    assertEquals("2=-1=0", hotAir.getAnswers().get(0).getValue());
  }
}
