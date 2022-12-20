package org.aoc.tmousaw.geometry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grid {

  private int maxX;
  private int minX;
  private int maxY;
  private int minY;

  Set<Point> points;
  Map<Point, Point> pointsMap;

  public Grid() {
    points = new HashSet<>();
    pointsMap = new HashMap<>();
    maxX = Integer.MIN_VALUE;
    minX = Integer.MAX_VALUE;
    maxY = Integer.MIN_VALUE;
    minY = Integer.MAX_VALUE;
  }

  public void addPoint(Point point) {
    points.add(point);
    pointsMap.put(point, point);
    if(point.getX() > maxX) {
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

  public Set<Point> getPoints() {
    return points;
  }

  public Map<Point, Point> getPointsMap() {
    return pointsMap;
  }
}
