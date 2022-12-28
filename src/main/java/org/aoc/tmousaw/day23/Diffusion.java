package org.aoc.tmousaw.day23;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.geometry.Grid;
import org.aoc.tmousaw.geometry.Point;

public class Diffusion extends AdventOfCodeSolver {

  private final Grid grid;

  public Diffusion() throws IOException {
    this("input.txt");
  }

  public Diffusion(String resourceFileName) throws IOException {
    super(resourceFileName);
    grid = new Grid();
  }

  public static void main(String[] args) throws IOException {
    Diffusion diffusion = new Diffusion();
    diffusion.solve();
    diffusion.printAnswers();
    System.out.println();
    diffusion.printTimings();
  }

  private static void checkMove(Set<Point> moveToPoints, Set<Point> moveToPointsToRemove, List<ProposedMove> proposedMoveList, Point p, int dx, int dy) {
    Point moveToPoint = new Point(p.getX() + dx, p.getY() + dy);
    if (!moveToPoints.add(moveToPoint)) {
      moveToPointsToRemove.add(moveToPoint);
    } else {
      proposedMoveList.add(new ProposedMove(p, moveToPoint));
    }
  }

  @Override
  public void solve() {
    for (int y = 0; y < getLinesOfInput().size(); y++) {
      String line = getLinesOfInput().get(y).trim();
      int lastIndex = 0;
      if (line.length() > 0) {
        while (true) {
          int index = line.indexOf('#');
          if (index == -1) {
            break;
          }
          grid.addPoint(new Point(index + lastIndex, y));
          lastIndex += index + 1;
          if (index + 1 == line.length()) {
            break;
          }
          line = line.substring(index + 1);
        }
      }
    }

    Queue<String> directionQueue = new ArrayDeque<>();
    directionQueue.add("north");
    directionQueue.add("south");
    directionQueue.add("west");
    directionQueue.add("east");

    for (int i = 0; i < 10; i++) {
      processRound(directionQueue);
    }

    addAnswer("Number of empty tiles", calculateEmptyTiles());

    int rounds = 10;
    int movesMade;
    do {
      movesMade = processRound(directionQueue);
      rounds++;
    } while (movesMade > 0);

    addAnswer("Rounds", rounds);
  }

  private int processRound(Queue<String> directionQueue) {
    Set<Point> moveToPoints = new HashSet<>();
    Set<Point> moveToPointsToRemove = new HashSet<>();
    List<ProposedMove> proposedMoveList = new ArrayList<>();
    for (Point p : grid.getPoints()) {
      if (hasAdjacentPoint(p)) {
        for (String direction : directionQueue) {
          if (hasNoAdjacentPointInDirection(p, direction)) {
            // Propose move.
            if ("north".equals(direction)) {
              checkMove(moveToPoints, moveToPointsToRemove, proposedMoveList, p, 0, -1);
              break;
            } else if ("south".equals(direction)) {
              checkMove(moveToPoints, moveToPointsToRemove, proposedMoveList, p, 0, 1);
              break;
            } else if ("west".equals(direction)) {
              checkMove(moveToPoints, moveToPointsToRemove, proposedMoveList, p, -1, 0);
              break;
            } else if ("east".equals(direction)) {
              checkMove(moveToPoints, moveToPointsToRemove, proposedMoveList, p, 1, 0);
              break;
            } else {
              throw new IllegalArgumentException("Unknown direction=" + direction);
            }
          }
        }
      }
    }

    // Remove duplicate moves
    proposedMoveList.removeIf(proposedMove -> moveToPointsToRemove.contains(proposedMove.getTo()));

    // Now do the moves
    for (ProposedMove proposedMove : proposedMoveList) {
      Point from = proposedMove.getFrom();

      grid.movePoint(from, proposedMove.getTo().getX() - proposedMove.getFrom().getX(), proposedMove.getTo().getY() - proposedMove.getFrom().getY());
    }

    // Now rotate the directions to check.
    String direction = directionQueue.remove();
    directionQueue.add(direction);

    return proposedMoveList.size();
  }

  private int calculateEmptyTiles() {
    return (grid.getMaxX() - grid.getMinX() + 1) * (grid.getMaxY() - grid.getMinY() + 1) - grid.getPoints().size();
  }

  private boolean hasNoAdjacentPointInDirection(Point point, String direction) {
    if ("north".equals(direction)) {
      return !grid.getPoints().contains(new Point(point.getX() - 1, point.getY() - 1))
          && !grid.getPoints().contains(new Point(point.getX(), point.getY() - 1))
          && !grid.getPoints().contains(new Point(point.getX() + 1, point.getY() - 1));
    } else if ("south".equals(direction)) {
      return !grid.getPoints().contains(new Point(point.getX() - 1, point.getY() + 1))
          && !grid.getPoints().contains(new Point(point.getX(), point.getY() + 1))
          && !grid.getPoints().contains(new Point(point.getX() + 1, point.getY() + 1));
    } else if ("west".equals(direction)) {
      return !grid.getPoints().contains(new Point(point.getX() - 1, point.getY() - 1))
          && !grid.getPoints().contains(new Point(point.getX() - 1, point.getY()))
          && !grid.getPoints().contains(new Point(point.getX() - 1, point.getY() + 1));
    } else if ("east".equals(direction)) {
      return !grid.getPoints().contains(new Point(point.getX() + 1, point.getY() - 1))
          && !grid.getPoints().contains(new Point(point.getX() + 1, point.getY()))
          && !grid.getPoints().contains(new Point(point.getX() + 1, point.getY() + 1));
    }

    throw new IllegalArgumentException("Illegal direction=" + direction);
  }

  private boolean hasAdjacentPoint(Point point) {
    if (!grid.getPoints().contains(point)) {
      return false;
    }

    for (int x = point.getX() - 1; x <= point.getX() + 1; x++) {
      for (int y = point.getY() - 1; y <= point.getY() + 1; y++) {
        if (x == point.getX() && y == point.getY()) {
          continue;
        }

        if (grid.getPoints().contains(new Point(x, y))) {
          return true;
        }
      }
    }

    return false;
  }

  private void printGrid() {
    for (int y = grid.getMinY(); y <= grid.getMaxY(); y++) {
      for (int x = grid.getMinX(); x <= grid.getMaxX(); x++) {
        if (grid.getPoints().contains(new Point(x, y))) {
          System.out.print('#');
        } else {
          System.out.print('.');
        }
      }
      System.out.println();
    }
  }
}
