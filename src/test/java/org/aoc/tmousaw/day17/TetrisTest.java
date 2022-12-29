package org.aoc.tmousaw.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TetrisTest {

  @Test
  void testTetris() throws IOException {
    Tetris tetris = new Tetris();
    tetris.solve();

    assertEquals("3179", tetris.getAnswers().get(0).getValue());
    //assertEquals("foo", tetris.getAnswers().get(1).getValue());
  }

  @Test
  void testTetrisSample() throws IOException {
    Tetris tetris = new Tetris("sample.txt");
    tetris.solve();

    assertEquals("3068", tetris.getAnswers().get(0).getValue());
    //assertEquals("1514285714288", tetris.getAnswers().get(1).getValue());
  }
}
