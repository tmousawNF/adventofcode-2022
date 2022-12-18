package org.aoc.tmousaw.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Tetris {
  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    String line;
    String jetPattern = null;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        jetPattern = line.trim();
      }
    }

//    System.out.println(jetPattern);
    Cave cave = new Cave(jetPattern);
    int nextRockIndex = 0;
    int numRocks = 0;
    int iterationNum = 0;
    while (numRocks < 2023) {
//    while (iterationNum < 30) {
      if (!cave.isRockFalling()) {
        int index = nextRockIndex % 5;
        if (index == 0) {
//          System.out.println("Adding horizontal rock.");
          cave.addRock(Rock.horizontalLine());
        } else if (index == 1) {
//          System.out.println("Adding plus rock.");
          cave.addRock(Rock.plus());
        } else if (index == 2) {
//          System.out.println("Adding jay rock.");
          cave.addRock(Rock.jay());
        } else if (index == 3) {
//          System.out.println("Adding vertical rock.");
          cave.addRock(Rock.verticalLine());
        } else {
//          System.out.println("Adding square rock.");
          cave.addRock(Rock.square());
        }
//        cave.print();
        nextRockIndex++;
        numRocks++;
      }

      cave.processStep(iterationNum);
//      cave.print();
//      System.out.println("Highest rock is " + cave.getHighestRock() + " units");
      iterationNum++;
    }

    cave.print();
    System.out.println("Number of rock units (Part 1): " + cave.getHighestRock());
  }
}
