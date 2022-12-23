package org.aoc.tmousaw.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class MonkeyMapTest {
  @Test
  public void testMonkeyMap() throws IOException {
    MonkeyMap monkeyMap = new MonkeyMap();
    monkeyMap.solve();

    assertEquals("89224", monkeyMap.getAnswers().get(0).getValue());
    assertEquals("foo", monkeyMap.getAnswers().get(1).getValue());
  }

  @Test
  public void testMonkeyMapSample() throws IOException {
    MonkeyMap monkeyMap = new MonkeyMap("sample.txt");
    monkeyMap.solve();

    assertEquals("6032", monkeyMap.getAnswers().get(0).getValue());
    assertEquals("6032", monkeyMap.getAnswers().get(1).getValue());
  }
}
