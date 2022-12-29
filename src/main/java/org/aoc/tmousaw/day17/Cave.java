package org.aoc.tmousaw.day17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.aoc.tmousaw.geometry.Point;

public class Cave {

  private final Map<Point, Point> rockMap;
  private final Set<Point> rocks;
  private Rock fallingRock;
  private int highestRock;
  private int fallingHighestRock;
  private final int rightmostLimit;
  private final int leftmostLimit;
  private int floor;
  private final String jetStream;

  public Cave(String jetStream) {
    rocks = new HashSet<>();
    rockMap = new HashMap<>();
    fallingRock = null;

    for (int i = 1; i < 7; i++) {
      addRock(new Point(i, 0));
    }

    // Presumably, the rightMostRock and leftMostRock are on either side of where the sand is coming from.
    leftmostLimit = 0;
    rightmostLimit = 8;

    // Don't know about any rocks yet. Therefore, highestRock would be the floor (i.e. 0).
    highestRock = 0;

    // The minimum of the fallingHighestRock
    fallingHighestRock = 4;

    floor = 0;
    this.jetStream = jetStream;
  }

  public void inferFloor() {
    floor = highestRock + 2;
    highestRock = floor;
  }

  public void addRock(Point p) {
    int size = rockMap.size();
    rocks.add(p);
    rockMap.put(p, p);
    assert rockMap.size() == size + 1;
  }

  public Set<Point> getRocks() {
    return rocks;
  }

  public int getHighestRock() {
    return highestRock;
  }

  public void addRock(Rock rock) {
    fallingRock = rock;
    fallingRock.adjustForHighestRock(highestRock);
    fallingHighestRock = fallingRock.getHighestPoint();
  }

  public boolean isRockFalling() {
    return fallingRock != null;
  }

  public void print() {
    for (int y = fallingHighestRock; y >= 0; y--) {
      for (int x = leftmostLimit; x <= rightmostLimit; x++) {
        if (x == 0 || x == 8) {
          if (y == 0) {
            System.out.print("+");
          } else {
            System.out.print("|");
          }
          continue;
        } else if (y == 0) {
          System.out.print("-");
          continue;
        }
        Point test = new Point(x, y);
        Point p = rockMap.get(test);
        if (p == null) {
          if (fallingRock != null && fallingRock.getPoints().contains(test)) {
            System.out.print("@");
          } else {
            System.out.print(".");
          }
        } else {
          System.out.print("#");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public void processStep(int i) {
    char move = jetStream.charAt(i % jetStream.length());

    if (move == '<') {
      if (canMove(-1, 0)) {
        fallingRock.moveLeft();
      }
    } else if (move == '>') {
      if (canMove(1, 0)) {
        fallingRock.moveRight();
      }
    } else {
      throw new IllegalArgumentException("Unknown move " + move);
    }

    // Now move down if possible.
    if (canMove(0, -1)) {
      fallingRock.moveDown();
    } else {
      // Can't move. The rock has come to rest.
      for (Point p : fallingRock.getPoints()) {
        addRock(p);
        if (p.getY() > highestRock) {
          highestRock = p.getY();
        }
      }

      fallingRock = null;
    }
  }

  private boolean canMove(int dx, int dy) {
    boolean canMove = true;
    for (Point p : fallingRock.getPoints()) {
      Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
      if (newPoint.getX() <= 0 || newPoint.getX() >= 8 || rockMap.containsKey(newPoint)) {
        return false;
      }
    }

    return true;
  }
}
