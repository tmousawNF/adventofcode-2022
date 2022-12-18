package org.aoc.tmousaw.lava;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grid3D {

  private int maxX;
  private int minX;
  private int maxY;
  private int minY;
  private int maxZ;
  private int minZ;

  Set<Point3D> points3D;
  Map<Point3D, Point3D> points3DMap;

  public Grid3D() {
    points3D = new HashSet<>();
    points3DMap = new HashMap<>();
    maxX = Integer.MIN_VALUE;
    minX = Integer.MAX_VALUE;
    maxY = Integer.MIN_VALUE;
    minY = Integer.MAX_VALUE;
    maxZ = Integer.MIN_VALUE;
    minZ = Integer.MAX_VALUE;
  }

  public void addPoint(Point3D point3D) {
    points3D.add(point3D);
    points3DMap.put(point3D, point3D);
    if (point3D.getX() > maxX) {
      maxX = point3D.getX();
    }
    if (point3D.getX() < minX) {
      minX = point3D.getX();
    }
    if (point3D.getY() > maxY) {
      maxY = point3D.getY();
    }
    if (point3D.getY() < minY) {
      minY = point3D.getY();
    }
    if (point3D.getZ() > maxZ) {
      maxZ = point3D.getZ();
    }
    if (point3D.getZ() < minZ) {
      minZ = point3D.getZ();
    }
  }

  public Set<Point3D> getPoints() {
    return points3D;
  }

  public Point3D getPoint(Point3D point3D) {
    return points3DMap.get(point3D);
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

  public int getMaxZ() {
    return maxZ;
  }

  public int getMinZ() {
    return minZ;
  }
}
