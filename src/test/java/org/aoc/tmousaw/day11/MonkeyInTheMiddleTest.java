package org.aoc.tmousaw.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class MonkeyInTheMiddleTest {
  @Test
  void testMonkeyInTheMiddle() throws IOException {
    MonkeyInTheMiddle monkeyInTheMiddle = new MonkeyInTheMiddle();
    monkeyInTheMiddle.solve();

    assertEquals("66802", monkeyInTheMiddle.getAnswers().get(0).getValue());
    assertEquals("21800916620", monkeyInTheMiddle.getAnswers().get(1).getValue());
  }

  @Test
  void testMonkeyInTheMiddleSample() throws IOException {
    MonkeyInTheMiddle monkeyInTheMiddle = new MonkeyInTheMiddle("sample.txt");
    monkeyInTheMiddle.solve();

    assertEquals("10605", monkeyInTheMiddle.getAnswers().get(0).getValue());
    assertEquals("2713310158", monkeyInTheMiddle.getAnswers().get(1).getValue());
  }
}
