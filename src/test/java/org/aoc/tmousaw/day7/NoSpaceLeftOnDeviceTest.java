package org.aoc.tmousaw.day7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class NoSpaceLeftOnDeviceTest {

  @Test
  void testNoSpaceLeftOnDevice() throws IOException {
    NoSpaceLeftOnDevice noSpaceLeftOnDevice = new NoSpaceLeftOnDevice();
    noSpaceLeftOnDevice.solve();

    assertEquals("1501149", noSpaceLeftOnDevice.getAnswers().get(0).getValue());
    assertEquals("10096985", noSpaceLeftOnDevice.getAnswers().get(1).getValue());
  }
}
