package org.aoc.tmousaw.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class RucksackPriority extends AdventOfCodeSolver {

  public RucksackPriority() throws IOException {
    super();
  }

  public static void main(String[] args) throws IOException {
    RucksackPriority rucksackPriority = new RucksackPriority();

    rucksackPriority.solve();
    rucksackPriority.printAnswers();
    System.out.println();
    rucksackPriority.printTimings();
  }

  @Override
  public void solve() {

    List<Rucksack> rucksackList = new ArrayList<>();
    for (String line : getLinesOfInput()) {
      rucksackList.add(new Rucksack(line));
    }

    int prioritySum = 0;
    for (Rucksack rucksack : rucksackList) {
      Set<Character> compartmentCopy = new HashSet<>(rucksack.getCompartmentOne());
      compartmentCopy.retainAll(rucksack.getCompartmentTwo());
      prioritySum += compartmentCopy.stream().mapToInt(Rucksack::getCharacterPriority).sum();
    }
    addAnswer("Sum of priority", prioritySum);

    prioritySum = 0;
    for (int i = 0; i < rucksackList.size() / 3; i++) {
      if (3*i+2 >= rucksackList.size()) {
        System.out.println("WARNING: Number of rucksacks was not divisible by 3. size=" + rucksackList.size());
        break;
      }

      Rucksack rucksack = rucksackList.get(3*i);
      Set<Character> commonSet = new HashSet<>(rucksack.getContents());
      rucksack = rucksackList.get(3*i+1);
      commonSet.retainAll(rucksack.getContents());
      rucksack = rucksackList.get(3*i+2);
      commonSet.retainAll(rucksack.getContents());
      prioritySum += commonSet.stream().mapToInt(Rucksack::getCharacterPriority).sum();
    }
    addAnswer("Sum of priority", prioritySum);
  }
}
