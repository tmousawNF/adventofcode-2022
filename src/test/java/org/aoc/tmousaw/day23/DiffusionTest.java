package org.aoc.tmousaw.day23;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class DiffusionTest {
  @Test
  public void testDiffusion() throws IOException {
    Diffusion diffusion = new Diffusion();
    diffusion.solve();

    assertEquals("4218", diffusion.getAnswers().get(0).getValue());
    assertEquals("976", diffusion.getAnswers().get(1).getValue());
  }

  @Test
  public void testDiffusionSample() throws IOException {
    Diffusion diffusion = new Diffusion("sample.txt");
    diffusion.solve();

    assertEquals("110", diffusion.getAnswers().get(0). getValue());
    assertEquals("20", diffusion.getAnswers().get(1).getValue());
  }
}
