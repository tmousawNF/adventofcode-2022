package org.aoc.tmousaw.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class RockPaperScissorsTest {

  @Test
  public void testRockPaperScissors() throws IOException {
    RockPaperScissors rockPaperScissors = new RockPaperScissors();
    rockPaperScissors.solve();

    assertEquals("10816", rockPaperScissors.getAnswers().get(0).getValue());
    assertEquals("11657", rockPaperScissors.getAnswers().get(1).getValue());
  }
}
