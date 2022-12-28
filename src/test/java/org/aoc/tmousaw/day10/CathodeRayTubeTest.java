package org.aoc.tmousaw.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CathodeRayTubeTest {
  @Test
  void testCathodeRayTube() throws IOException {
    CathodeRayTube cathodeRayTube = new CathodeRayTube();
    cathodeRayTube.solve();

    assertEquals("14240", cathodeRayTube.getAnswers().get(0).getValue());
  }

  @Test
  void testCathodeRayTubeSample() throws IOException {
    CathodeRayTube cathodeRayTube = new CathodeRayTube("sample.txt");
    cathodeRayTube.solve();

    assertEquals("13140", cathodeRayTube.getAnswers().get(0).getValue());
  }
}
