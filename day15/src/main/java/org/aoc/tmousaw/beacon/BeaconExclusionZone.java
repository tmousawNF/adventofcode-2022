package org.aoc.tmousaw.beacon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class BeaconExclusionZone {

  public static void main(String[] args) throws IOException {
    final long startTime = System.currentTimeMillis();
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    Cave cave = new Cave();
    String line;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        cave.addSensorAndBeacon(line.trim());
      }
    }

    System.out.println("Number of points with no beacon (Part 1): " + cave.countNoBeaconsAt(2000000));
    System.out.println("Tuning frequency (Part 2): " + cave.calculateTuningFrequency(0, 4000000));

    final long endTime = System.currentTimeMillis();
    System.out.println();
    System.out.println("Total execution time: " + (endTime - startTime));
  }
}
