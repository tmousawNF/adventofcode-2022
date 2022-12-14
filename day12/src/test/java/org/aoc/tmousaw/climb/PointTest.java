package org.aoc.tmousaw.climb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PointTest {

  @Test
  public void testConstruction() {
    Point p = new Point(1, 1);
    assertEquals(1, p.getX());
    assertEquals(1, p.getY());
  }

  @Test
  public void testMoveUp() {
    Point p = new Point();
    p.moveUp();

    assertEquals(0, p.getX());
    assertEquals(1, p.getY());
  }

  @Test
  public void testMoveDown() {
    Point p = new Point();
    p.moveDown();

    assertEquals(0, p.getX());
    assertEquals(-1, p.getY());
  }

  @Test
  public void testMoveLeft() {
    Point p = new Point();
    p.moveLeft();

    assertEquals(-1, p.getX());
    assertEquals(0, p.getY());
  }

  @Test
  public void testMoveRight() {
    Point p = new Point();
    p.moveRight();

    assertEquals(1, p.getX());
    assertEquals(0, p.getY());
  }

  @Test
  public void testMove() {
    Point p = new Point();
    p.move(5, 5);
    assertEquals(5, p.getX());
    assertEquals(5, p.getY());

    p.move(-4, 4);
    assertEquals(1, p.getX());
    assertEquals(9, p.getY());

    p.move(3, -3);
    assertEquals(4, p.getX());
    assertEquals(6, p.getY());

    p.move (-2, -2);
    assertEquals(2, p.getX());
    assertEquals(4, p.getY());

    // Make sure values can be negative.
    p.move(-10, 0);
    assertEquals(-8, p.getX());
    assertEquals(4, p.getY());

    p.move(8, -5);
    assertEquals(0, p.getX());
    assertEquals(-1, p.getY());
  }

  @Test
  public void assertThatTwoPointInstancesWithSameCoordinatesAreEqual() {
    Point p1 = new Point(5, 5);
    Point p2 = new Point(5, 5);

    assertEquals(p1, p2);
  }

  @Test
  public void testIsAdjacent() {
    Point p1 = new Point();
    Point p2 = new Point();

    // A point is not adjacent to itself.
    assertFalse(p1.isAdjacent(p2));

    p2.moveUp();
    assertTrue(p1.isAdjacent(p2));

    p2.moveLeft();
    assertTrue(p1.isAdjacent(p2));

    p2.moveDown();
    assertTrue(p1.isAdjacent(p2));

    p2.moveDown();
    assertTrue(p1.isAdjacent(p2));

    p2.moveRight();
    assertTrue(p1.isAdjacent(p2));

    p2.moveRight();
    assertTrue(p1.isAdjacent(p2));

    p2.moveUp();
    assertTrue(p1.isAdjacent(p2));

    p2.moveUp();
    assertTrue(p1.isAdjacent(p2));

    p2.moveUp();
    assertFalse(p1.isAdjacent(p2));
  }
}
