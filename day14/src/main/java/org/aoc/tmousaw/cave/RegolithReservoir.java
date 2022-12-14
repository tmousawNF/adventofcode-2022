package org.aoc.tmousaw.cave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RegolithReservoir {
  public static void main(String[]  args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    Cave cave = new Cave();
    String line;
    while ((line = br.readLine()) != null) {
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

    System.out.println("Units of sand (Part 1): " + unitsOfSand);

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

    System.out.println("Units of sand (Part 2): " + unitsOfSand);
  }

  public static void printCave(Cave cave) {
    cave.printTiles();
    System.out.println();
  }
}
