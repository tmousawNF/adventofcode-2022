package org.aoc.tmousaw.beacon;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cave {

  private final Set<Point> points;
  private final Map<Sensor, Beacon> sensorToBeaconMap;

  public Cave() {
    sensorToBeaconMap = new HashMap<>();
    points = new HashSet<>();
  }

  public void addSensorAndBeacon(String input) {
    int sensorX = Integer.parseInt(input.substring(input.indexOf("=") + 1, input.indexOf(",")));
    String input1 = input.substring(input.indexOf(",") + 1);
    int sensorY = Integer.parseInt(input1.substring(input1.indexOf("=") + 1, input1.indexOf(":")));
    String input2 = input1.substring(input1.indexOf("=") + 1);
    int beaconX = Integer.parseInt(input2.substring(input2.indexOf("=") + 1, input2.indexOf(",")));
    String input3 = input2.substring(input2.indexOf(",") + 1);
    int beaconY = Integer.parseInt(input3.substring(input3.indexOf("=") + 1));
    addSensorAndBeacon(sensorX, sensorY, beaconX, beaconY);

//    evaluateNoBeacons(sensorX, sensorY, beaconX, beaconY);
  }

  public void addSensorAndBeacon(int sensorX, int sensorY, int beaconX, int beaconY) {
    Sensor s = new Sensor(sensorX, sensorY);
    Beacon b = new Beacon(beaconX, beaconY);
    points.add(s);
    points.add(b);
    sensorToBeaconMap.put(s, b);
  }

  public int countNoBeaconsAt(int y) {
    int numNoBeacons = 0;
    RangeSet<Integer> xRangeSet = TreeRangeSet.create();

    for (Sensor s : sensorToBeaconMap.keySet()) {
      Beacon b = sensorToBeaconMap.get(s);
      int distance = Math.abs(s.getX() - b.getX()) + Math.abs(s.getY() - b.getY());
      if (y > s.getY() - distance && y < s.getY() + distance) {
        xRangeSet.add(Range.closed(s.getX() - distance + Math.abs(s.getY() - y), s.getX() + distance - Math.abs(s.getY() - y)));
      }
    }

//    System.out.println(xRangeSet);
    for (Range<Integer> r : xRangeSet.asRanges()) {
      numNoBeacons += r.upperEndpoint() - r.lowerEndpoint() + 1;
    }

    for (Point p : points) {
      if (p.getY() == y) {
        numNoBeacons--;
      }
    }

    return numNoBeacons;
  }

  public BigInteger calculateTuningFrequency(int min, int max) {
    int x = 0, y;

    for (y = min; y <= max; y++) {
      RangeSet<Integer> xRangeSet = TreeRangeSet.create();
      for (Sensor s : sensorToBeaconMap.keySet()) {
        Beacon b = sensorToBeaconMap.get(s);
        int distance = Math.abs(s.getX() - b.getX()) + Math.abs(s.getY() - b.getY());
        if (y > s.getY() - distance && y < s.getY() + distance) {
          xRangeSet.add(Range.closed(s.getX() - distance + Math.abs(s.getY() - y), s.getX() + distance - Math.abs(s.getY() - y)));
        }
      }

      boolean foundEnclosingRange = false;
      for (Range<Integer> r : xRangeSet.asRanges()) {
        if (r.encloses(Range.closed(min, max))) {
          foundEnclosingRange = true;
          break;
        }
      }

      if (!foundEnclosingRange) {
        // Find x
//        System.out.println(xRangeSet);
        for (Range<Integer> r : xRangeSet.asRanges()) {
          if (r.isConnected(Range.closed(0, 20))) {
            if (r.lowerEndpoint() > min) {
              x = r.lowerEndpoint() - 1;
            } else if (r.upperEndpoint() < max) {
              x = r.upperEndpoint() + 1;
            }
            break;
          }
        }
        break;
      }
    }
//    System.out.printf("(%d, %d)%n", x, y);

    return new BigInteger(String.valueOf(x)).multiply(new BigInteger(String.valueOf(4000000))).add(new BigInteger(String.valueOf(y)));
  }
}
