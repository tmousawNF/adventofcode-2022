package org.aoc.tmousaw.day23;

import org.aoc.tmousaw.geometry.Point;

public class ProposedMove {
  private final Point from;
  private final Point to;

  public ProposedMove(Point from, Point to) {
    this.from = from;
    this.to = to;
  }

  public Point getFrom() {
    return from;
  }

  public Point getTo() {
    return to;
  }

  @Override
  public String toString() {
    return String.format("%s -> %s", from, to);
  }
}
