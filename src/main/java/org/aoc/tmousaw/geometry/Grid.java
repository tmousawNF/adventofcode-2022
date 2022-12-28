package org.aoc.tmousaw.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grid {

  List<Point> points;
  Map<Point, Point> pointsMap;
  private int maxX;
  private int minX;
  private int maxY;
  private int minY;

  public Grid() {
    points = new ArrayList<>();
    pointsMap = new TreeMap<>();
    maxX = Integer.MIN_VALUE;
    minX = Integer.MAX_VALUE;
    maxY = Integer.MIN_VALUE;
    minY = Integer.MAX_VALUE;
  }

  public void addPoint(Point point) {
    points.add(point);
    pointsMap.put(point, point);
    checkForNewBoundaries(point);
  }

  public void movePoint(Point point, int dx, int dy) {
    point.move(dx, dy);
    checkForNewBoundaries(point);
  }

  private void checkForNewBoundaries(Point point) {
    if (point.getX() > maxX) {
      maxX = point.getX();
    }
    if (point.getX() < minX) {
      minX = point.getX();
    }
    if (point.getY() > maxY) {
      maxY = point.getY();
    }
    if (point.getY() < minY) {
      minY = point.getY();
    }
  }

  public int getMaxX() {
    return maxX;
  }

  public int getMinX() {
    return minX;
  }

  public int getMaxY() {
    return maxY;
  }

  public int getMinY() {
    return minY;
  }

  public List<Point> getPoints() {
    return points;
  }

  public Map<Point, Point> getPointsMap() {
    return pointsMap;
  }
}
