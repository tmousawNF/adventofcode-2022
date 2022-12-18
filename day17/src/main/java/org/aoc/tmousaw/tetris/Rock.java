package org.aoc.tmousaw.tetris;

import java.util.HashSet;
import java.util.Set;

public class Rock {
  private final Set<Point> points;

  private Rock(Set<Point> points) {
    this.points = points;
  }

  public static Rock horizontalLine() {
    Set<Point> pointSet = new HashSet<>();
    for (int x = 3; x <= 6; x++) {
      pointSet.add(new Point(x, 4));
    }
    return new Rock(pointSet);
  }

  public static Rock plus() {
    Set<Point> pointSet = new HashSet<>();
    pointSet.add(new Point(4, 4));
    pointSet.add(new Point(3, 5));
    pointSet.add(new Point(4, 5));
    pointSet.add(new Point(5, 5));
    pointSet.add(new Point(4, 6));
    return new Rock(pointSet);
  }

  public static Rock jay() {
    Set<Point> pointSet = new HashSet<>();
    pointSet.add(new Point(3, 4));
    pointSet.add(new Point(4, 4));
    pointSet.add(new Point(5, 4));
    pointSet.add(new Point(5, 5));
    pointSet.add(new Point(5, 6));
    return new Rock(pointSet);
  }

  public static Rock verticalLine() {
    Set<Point> pointSet = new HashSet<>();
    for (int y = 4; y <= 7; y++) {
      pointSet.add(new Point(3, y));
    }
    return new Rock(pointSet);
  }

  public static Rock square() {
    Set<Point> pointSet = new HashSet<>();
    for (int x = 3; x <=4; x++) {
      for (int y = 4; y <= 5; y++) {
        pointSet.add(new Point(x, y));
      }
    }
    return new Rock(pointSet);
  }

  public void adjustForHighestRock(int highestRock) {
    Set<Point> tmpSet = new HashSet<>(points);
    for (Point p : tmpSet) {
      points.remove(p);
      p.move(0, highestRock);
    }

    points.addAll(tmpSet);
  }

  public int getLeftmostPoint() {
    int leftmostPoint = 8;
    for (Point p : points) {
      if (p.getX() < leftmostPoint) {
        leftmostPoint = p.getX();
      }
    }

    return leftmostPoint;
  }

  public int getRightmostPoint() {
    int rightMostPoint = 0;
    for (Point p : points) {
      if (p.getX() > rightMostPoint) {
        rightMostPoint = p.getX();
      }
    }

    return rightMostPoint;
  }

  private int getLowestPoint() {
    int lowestPoint = Integer.MAX_VALUE;
    for (Point p : points) {
      if (p.getY() < lowestPoint) {
        lowestPoint = p.getY();
      }
    }

    return lowestPoint;
  }


  public int getHighestPoint() {
    int highestPoint = 0;
    for (Point p : points) {
      if (p.getY() > highestPoint) {
        highestPoint = p.getY();
      }
    }

    return highestPoint;
  }

  public void moveLeft() {
    if (getLeftmostPoint() <= 1) {
      throw new IllegalArgumentException("Rock can not move left");
    }

    Set<Point> tmpSet = new HashSet<>(points);
    for (Point p : tmpSet) {
      points.remove(p);
      p.moveLeft();
    }

    points.addAll(tmpSet);
  }

  public void moveRight() {
    if (getRightmostPoint() >= 7) {
      throw new IllegalArgumentException("Rock can not move right");
    }

    Set<Point> tmpSet = new HashSet<>(points);
    for (Point p : tmpSet) {
      points.remove(p);
      p.moveRight();
    }

    points.addAll(tmpSet);
  }

  public void moveDown() {
    if (getLowestPoint() <= 1) {
      throw new IllegalArgumentException("Rock can not move through the floor");
    }

    Set<Point> tmpSet = new HashSet<>(points);
    for (Point p : tmpSet) {
      points.remove(p);
      p.moveDown();
    }

    points.addAll(tmpSet);
  }

  public Set<Point> getPoints() {
    return points;
  }
}
