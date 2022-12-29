package org.aoc.tmousaw.day14;

import java.io.IOException;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class RegolithReservoir extends AdventOfCodeSolver {
  private final Cave cave;
  public RegolithReservoir() throws IOException {
    this("input.txt");
  }

  public RegolithReservoir(String resourceFileName) throws IOException {
    super(resourceFileName);
    cave = new Cave();
  }

  public static void main(String[]  args) throws IOException {
    RegolithReservoir regolithReservoir = new RegolithReservoir();
    regolithReservoir.solve();
    regolithReservoir.printAnswers();
    System.out.println();
    regolithReservoir.printTimings();
  }

  @Override
  public void solve() {
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        cave.addRockFormation(line.trim());
      }
    }

    // printCave(cave);
    int unitsOfSand = 0;
    try {
      while (true) {
        cave.dropGrainOfSand();
        unitsOfSand++;
        // printCave(cave);
      }
    } catch (Exception e) {
      // Do nothing.
    }

    addAnswer("Units of sand", unitsOfSand);

    cave.removeSand();
    // printCave(cave);
    cave.inferFloor();
    unitsOfSand = 0;
    try {
      while (true) {
        cave.dropGrainOfSand();
        unitsOfSand++;
        // printCave(cave);
      }
    } catch (Exception e) {
      // Do nothing.
    }

    addAnswer("Units of sand", unitsOfSand);
  }

  public static void printCave(Cave cave) {
    cave.printTiles();
    System.out.println();
  }
}
