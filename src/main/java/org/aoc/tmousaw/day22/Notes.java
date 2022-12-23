package org.aoc.tmousaw.day22;

import org.aoc.tmousaw.geometry.Grid;
import org.aoc.tmousaw.geometry.Point;

public class Notes extends Grid {

  private Point startingPosition;

  public Notes() {
    startingPosition = null;
  }

  @Override
  public void addPoint(Point p) {
    super.addPoint(p);

    // The first point added will always be the starting position.
    if (startingPosition == null) {
      startingPosition = p;
    }
  }

  public void print() {
    for (int y = getMinY(); y <= getMaxY(); y++) {
      for (int x = getMinX(); x <= getMaxX(); x++) {
        Point p = getPointsMap().get(new Point(x, y));
        if (p == null) {
          System.out.print(" ");
        } else if (p instanceof Tile) {
            System.out.print(".");
        } else if (p instanceof Rock) {
          System.out.print("#");
        }
      }
      System.out.println();
    }
  }

  public Point getStartingPosition() {
    return startingPosition;
  }
}
