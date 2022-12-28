package org.aoc.tmousaw.day10;

import java.io.IOException;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class CathodeRayTube extends AdventOfCodeSolver {

  public CathodeRayTube() throws IOException {
    this("input.txt");
  }

  public CathodeRayTube(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    CathodeRayTube cathodeRayTube = new CathodeRayTube();
    cathodeRayTube.solve();
    cathodeRayTube.printAnswers();
    System.out.println();
    cathodeRayTube.printTimings();
  }

  public static void processCycle(int cycle, int registerX) {
    if (Math.abs((cycle % 40) - registerX) <= 1) {
      System.out.print("#");
    } else {
      System.out.print(".");
    }

    if ((cycle + 1) % 40 == 0) {
      System.out.println();
    }
  }

  @Override
  public void solve() {
    int registerX = 1;
    int cycles = 0;
    int sumOfSignalStrength = 0;
    System.out.println("Eight capital letters (Part 2) rendered below:");

    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        String input = line.trim();

        String[] tokens = input.split(" ");

        if (tokens.length > 0) {
          String command = tokens[0];
          if ("noop".equals(command)) {
            processCycle(cycles, registerX);
            cycles++;
            if ((cycles == 20) || (cycles - 20) % 40 == 0) {
              sumOfSignalStrength += cycles * registerX;
            }
          } else if ("addx".equals(command)) {
            if (((cycles + 1 == 20) || ((cycles + 2) == 20) || (((cycles - 20 + 1) % 40) == 0) || (((cycles - 20 + 2) % 40) == 0))) {
              int tempCycles = cycles + 1;
              if ((tempCycles != 20) && ((tempCycles - 20) % 40) != 0) {
                tempCycles++;
              }
              assert (tempCycles == 20) || ((tempCycles - 20) % 40) == 0;
              sumOfSignalStrength += tempCycles * registerX;
            }
            processCycle(cycles, registerX);
            cycles++;
            processCycle(cycles, registerX);
            registerX += Integer.parseInt(tokens[1]);
            cycles++;
          }
        } else {
          System.out.println("WARNING: Line was empty. line=" + line);
        }
      }
    }

    addAnswer("Sum of Signals", sumOfSignalStrength);
  }
}
