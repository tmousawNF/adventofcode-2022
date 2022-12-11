package org.aoc.tmousaw.ropebridge;

public class GridPosition {
  private Integer x;
  private Integer y;

  public GridPosition() {
    x = 0;
    y = 0;
  }

  public GridPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void moveLeft() {
    x = x - 1;
  }

  public void moveRight() {
    x = x + 1;
  }

  public void moveUp() {
    y = y + 1;
  }

  public void moveDown() {
    y = y - 1;
  }

  public boolean isTouching(GridPosition pos) {
    return Math.abs(x - pos.x) <= 1 && Math.abs(y - pos.y) <= 1;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof GridPosition) {
      GridPosition pos = (GridPosition) obj;
      return pos.x.equals(x) && pos.y.equals(y);
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
}
