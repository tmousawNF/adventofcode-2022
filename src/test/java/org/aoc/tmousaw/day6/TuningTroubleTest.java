package org.aoc.tmousaw.day6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TuningTroubleTest {
  @Test
  public void testTuningTrouble() throws IOException {
    TuningTrouble tuningTrouble = new TuningTrouble();
    tuningTrouble.solve();

    assertEquals("1582", tuningTrouble.getAnswers().get(0).getValue());
    assertEquals("3588", tuningTrouble.getAnswers().get(1).getValue());
  }
}
