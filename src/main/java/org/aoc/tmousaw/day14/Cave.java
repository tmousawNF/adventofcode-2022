package org.aoc.tmousaw.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.aoc.tmousaw.geometry.Point;

public class Cave {

  private Set<Point> tiles;
  private final Map<Point, Point> tilesMap;
  private int lowestRock; // Called lowestRock, but actually the greatest Y value.
  private int rightMostRock;
  private int leftMostRock;
  private int floor;

  public Cave() {
    tiles = new HashSet<>();
    tilesMap = new HashMap<>();

    // Presumably, the rightMostRock and leftMostRock are on either side of where the sand is coming from.
    rightMostRock = 499;
    leftMostRock = 501;

    // Don't know about any rocks yet. Therefore, lowestRock would be the ceiling (i.e. 0).
    lowestRock = 0;

    // This constructor assumes no floor.
    floor = Integer.MAX_VALUE;
  }

  public void inferFloor() {
    floor = lowestRock + 2;
    lowestRock = floor;
  }

  public boolean hasFloor() {
    return floor != Integer.MAX_VALUE;
  }

  public void addRockFormation(String input) {
    String[] points = input.split(" *-> *");
    List<Rock> rockFormationPoints = new ArrayList<>();
    for (String point : points) {
      String[] pointParts = point.split(",");
      if (pointParts.length < 2) {
        System.err.println("Point specification was incomplete. point=" + point);
        continue;
      }
      rockFormationPoints.add(new Rock(Integer.parseInt(pointParts[0]), Integer.parseInt(pointParts[1])));
    }

    for (int i = 0; i < rockFormationPoints.size() - 1; i++) {
      Rock r1 = rockFormationPoints.get(i);
      Rock r2 = rockFormationPoints.get(i + 1);
      int tmpLeftmostRock = Math.min(r1.getX(), r2.getX());
      leftMostRock = Math.min(leftMostRock, tmpLeftmostRock);
      int tmpRightmostRock = Math.max(r1.getX(), r2.getX());
      rightMostRock = Math.max(rightMostRock, tmpRightmostRock);
      int tmpLowestRock = Math.max(r1.getY(), r2.getY());
      lowestRock = Math.max(lowestRock, tmpLowestRock);

      assert r1.getX() == r2.getX() || r1.getY() == r2.getY();
      addPoint(r1);
      addPoint(r2);

      if (r1.getX() < r2.getX()) {
        for (int j = r1.getX() + 1; j < r2.getX(); j++) {
          addRock(j, r1.getY());
        }
      } else if (r1.getX() > r2.getX()) {
        for (int j = r1.getX() - 1; j > r2.getX(); j--) {
          addRock(j, r1.getY());
        }
      } else if (r1.getY() < r2.getY()) {
        for (int j = r1.getY() + 1; j < r2.getY(); j++) {
          addRock(r1.getX(), j);
        }
      } else if (r1.getY() > r2.getY()) {
        for (int j = r1.getY() - 1; j > r2.getY(); j--) {
          addRock(r1.getX(), j);
        }
      }
    }
  }

  public void addPoint(Point p) {
    tiles.add(p);
    tilesMap.put(p, p);
  }

  public void addRock(int x, int y) {
    Rock r = new Rock(x, y);
    addPoint(r);
  }

  public Set<Point> getTiles() {
    return tiles;
  }

  public void dropGrainOfSand() throws CannotFallException, IntoTheAbyssException {
    Sand grain = new Sand();

    if (tiles.contains(grain)) {
      throw new CannotFallException(grain);
    }

    while (true) {
      if (grain.getX() < leftMostRock) {
        leftMostRock = grain.getX();
      } else if (grain.getX() > rightMostRock) {
        rightMostRock = grain.getX();
      }
      if (grain.getY() >= lowestRock) {
        throw new IntoTheAbyssException(grain);
      }
      Sand s = new Sand(grain.getX(), grain.getY() + 1); // Move down one.
      if (!tiles.contains(s) && (!hasFloor() || (hasFloor() && grain.getY() < floor - 1))) {
        grain = s;
        continue;
      }
      s = new Sand(grain.getX() - 1, grain.getY() + 1); // Down diagonally to the left.
      if (!tiles.contains(s) && (!hasFloor() || (hasFloor() && grain.getY() < floor - 1))) {
        grain = s;
        continue;
      }
      s = new Sand(grain.getX() + 1, grain.getY() + 1); // Down diagonally to the right.
      if (!tiles.contains(s) && (!hasFloor() || (hasFloor() && grain.getY() < floor - 1))) {
        grain = s;
        continue;
      }

      break;
    }

    addPoint(grain);
  }

  public void printTiles() {
    for (int y = 0; y <= lowestRock; y++) {
      for (int x = leftMostRock; x <= rightMostRock; x++) {
        if (x == 500 && y == 0) {
          System.out.print("+");
        } else {
          Point p = tilesMap.get(new Point(x, y));
          if (p == null) {
            if (hasFloor() && y == floor) {
              System.out.print("#");
            } else {
              System.out.print(".");
            }
          } else if (p instanceof Rock) {
            System.out.print("#");
          } else if (p instanceof Sand) {
            System.out.print("o");
          }
        }
      }
      System.out.println();
    }
  }

  public void removeSand() {
    tilesMap.clear();
    tiles = tiles.stream().filter(p -> p instanceof Rock).collect(Collectors.toSet());
    for (Point p : tiles) {
      tilesMap.put(p, p);
    }
  }
}
