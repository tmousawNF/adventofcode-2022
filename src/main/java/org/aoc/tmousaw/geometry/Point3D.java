package org.aoc.tmousaw.geometry;

import java.util.Objects;

public class Point3D implements Comparable<Point3D> {

  private final Point p;
  private final Integer z;

  public Point3D(int x, int y, int z) {
    p = new Point(x, y);
    this.z = z;
  }

  public Integer getX() {
    return p.getX();
  }

  public Integer getY() {
    return p.getY();
  }

  public Integer getZ() {
    return z;
  }

  private Point getPoint() {
    return p;
  }

  @Override
  public int compareTo(Point3D p3d) {
    if (this.equals(p3d)) {
      return 0;
    }

    if (!this.p.equals(p3d.p)) {
      return this.p.compareTo(p3d.getPoint());
    }

    return z.compareTo(p3d.getZ());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    Point3D point3D = (Point3D) o;
    return Objects.equals(p, point3D.p) && Objects.equals(z, point3D.z);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p, z);
  }
}