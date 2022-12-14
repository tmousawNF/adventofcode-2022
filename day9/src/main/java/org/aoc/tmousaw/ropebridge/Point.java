package org.aoc.tmousaw.ropebridge;

import java.util.Objects;

public class Point implements Comparable<Point> {
  private Integer x;
  private Integer y;

  public Point() {
    this.x = 0;
    this.y = 0;
  }
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void move(int xDelta, int yDelta) {
    x += xDelta;
    y += yDelta;
  }

  public void moveUp() {
    move(0, 1);
  }

  public void moveDown() {
    move(0, -1);
  }

  public void moveLeft() {
    move(-1, 0);
  }

  public void moveRight() {
    move(1, 0);
  }

  public boolean isAdjacent(Point p) {
    return Math.abs(x - p.x) <= 1 && Math.abs(y - p.y) <= 1;
  }

  @Override
  public int compareTo(Point point) {
    if (this.equals(point)) {
      return 0;
    }

    if (!Objects.equals(this.x, point.x)) {
      return this.x.compareTo(point.x);
    }

    return this.y.compareTo(point.y);
  }


  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point p = (Point) obj;
      return p.x.equals(x) && p.y.equals(y);
    }

    return false;
  }

  @Override
  public final int hashCode() {
    int prime = 31;
    int result = 17;
    result = prime * result + x.hashCode();
    result = prime * result + y.hashCode();

    return result;
  }

  @Override
  public String toString() {
    return "(" + x +", " + y +")";
  }
}
