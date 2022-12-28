package org.aoc.tmousaw.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class BlizzardMazeTest {
  @Test
  @Disabled("because this test takes a while")
  public void testBlizzard() throws IOException {
    BlizzardMaze blizzardMaze = new BlizzardMaze();
    blizzardMaze.solve();

    assertEquals("260", blizzardMaze.getAnswers().get(0).getValue());
    assertEquals("747", blizzardMaze.getAnswers().get(1).getValue());
  }

  @Test
  public void testBlizzardSample() throws IOException {
    BlizzardMaze blizzardMaze = new BlizzardMaze("sample.txt");
    blizzardMaze.solve();

    assertEquals("18", blizzardMaze.getAnswers().get(0).getValue());
    assertEquals("54", blizzardMaze.getAnswers().get(1).getValue());
  }
}
