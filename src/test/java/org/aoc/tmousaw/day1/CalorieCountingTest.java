package org.aoc.tmousaw.day1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CalorieCountingTest {

  @Test
  public void testCalorieCounting() throws IOException {
    CalorieCounting calorieCounting = new CalorieCounting();

    calorieCounting.solve();

    assertEquals("67027", calorieCounting.getAnswers().get(0).getValue());
    assertEquals("197291", calorieCounting.getAnswers().get(1).getValue());
  }
}
