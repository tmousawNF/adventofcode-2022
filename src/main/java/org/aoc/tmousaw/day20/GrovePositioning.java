package org.aoc.tmousaw.day20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class GrovePositioning extends AdventOfCodeSolver {

  public GrovePositioning() throws IOException {
    super();
  }

  public GrovePositioning(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    GrovePositioning grovePositioning = new GrovePositioning();
    grovePositioning.solve();
    grovePositioning.printAnswers();
    System.out.println();
    grovePositioning.printTimings();
  }

  @Override
  public void solve() {
    List<IndexedLong> indexedLongs = new ArrayList<>();
    IndexedLong zero = null;
    for (int i = 0; i < getLinesOfInput().size(); i++) {
      String line = getLinesOfInput().get(i).trim();
      long value = Integer.parseInt(line);
      indexedLongs.add(new IndexedLong(value, i));
      if (value == 0) {
        zero = new IndexedLong(value, i);
      }
    }

    List<IndexedLong> indexedLongsCopy = new ArrayList<>(indexedLongs);
    mix(1, indexedLongs, indexedLongsCopy);

    int startIndex = indexedLongsCopy.indexOf(zero);
    addAnswer("Sum of coordinates",
        indexedLongsCopy.get((startIndex + 1000) % indexedLongsCopy.size()).getValue() + indexedLongsCopy.get((startIndex + 2000) % indexedLongsCopy.size())
            .getValue() + indexedLongsCopy.get((startIndex + 3000) % indexedLongsCopy.size()).getValue());

    long decryptionKey = 811589153;
    indexedLongs.replaceAll(l -> new IndexedLong(l.getValue() * decryptionKey, l.getIndex()));
    indexedLongsCopy = new ArrayList<>(indexedLongs);
    mix(10, indexedLongs, indexedLongsCopy);
    startIndex = indexedLongsCopy.indexOf(zero);
    addAnswer("Sum of coordinates",
        indexedLongsCopy.get((startIndex + 1000) % indexedLongsCopy.size()).getValue() + indexedLongsCopy.get((startIndex + 2000) % indexedLongsCopy.size())
            .getValue() + indexedLongsCopy.get((startIndex + 3000) % indexedLongsCopy.size()).getValue());
  }

  public void mix(int iterations, List<IndexedLong> indexedLongs, List<IndexedLong> indexedLongsCopy) {
    for (int i = 0; i < iterations; i++) {
      for (IndexedLong iLong : indexedLongs) {
        int index = indexedLongsCopy.indexOf(iLong);
        indexedLongsCopy.remove(index);
        int newIndex = Math.floorMod(index + iLong.getValue(), indexedLongsCopy.size());
        indexedLongsCopy.add(newIndex, iLong);
      }
    }
  }
}
