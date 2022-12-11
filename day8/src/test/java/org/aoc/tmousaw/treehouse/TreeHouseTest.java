package org.aoc.tmousaw.treehouse;

import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

public class TreeHouseTest {

  @Test
  public void testBackwardIterator() {
    List<Integer> intList = List.of(1, 0);

    ListIterator<Integer> listIterator = intList.listIterator(2);

    while(listIterator.hasPrevious()) {
      System.out.println(listIterator.previous());
    }
  }
}
