package org.aoc.tmousaw.day17;

import java.io.IOException;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class Tetris extends AdventOfCodeSolver {

  public Tetris() throws IOException {
    this("input.txt");
  }

  public Tetris(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    Tetris tetris = new Tetris();
    tetris.solve();
    tetris.printAnswers();
    System.out.println();
    tetris.printTimings();
  }

  @Override
  public void solve() {
    String jetPattern = null;
    for (String line : getLinesOfInput()) {
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
    addAnswer("Number of rock units", cave.getHighestRock());
  }
}
