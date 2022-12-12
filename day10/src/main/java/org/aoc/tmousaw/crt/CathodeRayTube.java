package org.aoc.tmousaw.crt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CathodeRayTube {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    int registerX = 1;
    int cycles = 0;
    int sumOfSignalStrength = 0;
    String line;
    while ((line = br.readLine()) != null) {
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

    System.out.println("Sum of Signals (Part 1): " + sumOfSignalStrength);
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
}
