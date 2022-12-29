package org.aoc.tmousaw.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class HillClimbingTest {
  @Test
  void testHillClimbing() throws IOException {
    HillClimbing hillClimbing = new HillClimbing();
    hillClimbing.solve();

    assertEquals("352", hillClimbing.getAnswers().get(0).getValue());
    assertEquals("345", hillClimbing.getAnswers().get(1).getValue());
  }

  @Test
  void testHillClimbingSample() throws IOException {
    HillClimbing hillClimbing = new HillClimbing("sample.txt");
    hillClimbing.solve();

    assertEquals("31", hillClimbing.getAnswers().get(0).getValue());
    assertEquals("29", hillClimbing.getAnswers().get(1).getValue());
  }
}
