package org.aoc.tmousaw.day4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CampCleanupTest {

  @Test
  public void testCampCleanup() throws IOException {
    CampCleanup campCleanup = new CampCleanup();
    campCleanup.solve();

    assertEquals("500", campCleanup.getAnswers().get(0).getValue());
    assertEquals("815", campCleanup.getAnswers().get(1).getValue());
  }
}
