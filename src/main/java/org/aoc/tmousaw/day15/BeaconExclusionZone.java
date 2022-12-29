package org.aoc.tmousaw.day15;

import java.io.IOException;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class BeaconExclusionZone extends AdventOfCodeSolver {
  private final Cave cave;
  private final int rowPart1;
  private final int rowPart2;

  public BeaconExclusionZone(int rowPart1, int rowPart2) throws IOException {
    this("input.txt", rowPart1, rowPart2);
  }

  public BeaconExclusionZone(String resourceFileName, int rowPart1, int rowPart2) throws IOException {
    super(resourceFileName);
    cave = new Cave();
    this.rowPart1 = rowPart1;
    this.rowPart2 = rowPart2;
  }

  public static void main(String[] args) throws IOException {
    BeaconExclusionZone beaconExclusionZone = new BeaconExclusionZone(2000000, 4000000);
    beaconExclusionZone.solve();
    beaconExclusionZone.printAnswers();
    System.out.println();
    beaconExclusionZone.printTimings();
  }

  @Override
  public void solve() {
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        cave.addSensorAndBeacon(line.trim());
      }
    }

    addAnswer("Number of points with no beacon", cave.countNoBeaconsAt(rowPart1));
    addAnswer("Tuning frequency", cave.calculateTuningFrequency(0, rowPart2));
  }
}
