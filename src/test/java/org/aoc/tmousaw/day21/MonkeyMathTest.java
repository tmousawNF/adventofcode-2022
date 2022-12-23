package org.aoc.tmousaw.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class MonkeyMathTest {

  @Test
  public void testMonkeyMath() throws IOException {
    MonkeyMath monkeyMath = new MonkeyMath();
    monkeyMath.solve();
    assertEquals("276156919469632", monkeyMath.getAnswers().get(0).getValue());
  }

  @Test
  public void testMonkeyMathSample() throws IOException {
    MonkeyMath monkeyMath = new MonkeyMath("sample.txt");
    monkeyMath.solve();
    assertEquals("152", monkeyMath.getAnswers().get(0).getValue());
    assertEquals("301", monkeyMath.getAnswers().get(1).getValue());
  }
}
