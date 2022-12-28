package org.aoc.tmousaw.day8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

public class TreeHouseTest {
  @Test
  void testTreeHouse() throws IOException {
    TreeHouse treeHouse = new TreeHouse();
    treeHouse.solve();

    assertEquals("1825", treeHouse.getAnswers().get(0).getValue());
    assertEquals("235200", treeHouse.getAnswers().get(1).getValue());
  }


  @Test
  public void testBackwardIterator() {
    List<Integer> intList = List.of(1, 0);

    ListIterator<Integer> listIterator = intList.listIterator(2);

    int expected = 0;
    while(listIterator.hasPrevious()) {
      assertEquals(expected++, listIterator.previous());
    }
  }
}
