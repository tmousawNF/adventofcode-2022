package org.aoc.tmousaw.day22;

import java.util.Objects;
import org.aoc.tmousaw.geometry.Point;

public class Position {

  private Point point;
  private Orientation orientation;

  public Position(Point point, Orientation orientation) {
    this.point = point;
    this.orientation = orientation;
  }

  public Position(Position position) {
    this.point = new Point(position.point);
    this.orientation = position.orientation;
  }

  public Point getPoint() {
    return point;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public void turn(char direction) {
    orientation = orientation.turn(direction);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return Objects.equals(point, position.point) && orientation == position.orientation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(point, orientation);
  }
}