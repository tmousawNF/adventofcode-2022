package org.aoc.tmousaw.day12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.geometry.Point;

public class HillClimbing extends AdventOfCodeSolver {

  public HillClimbing() throws IOException {
    this("input.txt");
  }

  public HillClimbing(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    HillClimbing hillClimbing = new HillClimbing();
    hillClimbing.solve();
    hillClimbing.printAnswers();
    System.out.println();
    hillClimbing.printTimings();
  }

  @Override
  public void solve() {
    Map<Point, Integer> grid = new HashMap<>();
    Point start = null, end = null;
    for (int row = 0; row < getLinesOfInput().size(); row++) {
      String s = getLinesOfInput().get(row);
      for (int col = 0; col < s.length(); col++) {
        Point point = new Point(row, col);
        char c = s.charAt(col);
        if (c == 'S') {
          start = point;
          c = 'a';
        } else if (c == 'E') {
          end = point;
          c = 'z';
        }
        grid.put(point, c - 'a');
      }
    }

    addAnswer("Shortest number of steps to end location", findShortestPath(start, end, grid, true));
    addAnswer("Shortest number of steps to end location", findShortestPath(start, end, grid, false));
  }

  private static int findShortestPath(Point start, Point end, Map<Point, Integer> grid, boolean exactStart) {
    Map<Point, Integer> shortestPath = new HashMap<>();
    assert start != null;
    shortestPath.put(start, 0);

    List<Point> queue = new ArrayList<>();
    queue.add(start);
    while (queue.size() > 0) {
      Point p = queue.remove(0);
      int height = grid.get(p);
      int pathLen = shortestPath.get(p);

      Point up = new Point(p.getX() - 1, p.getY());
      Point down = new Point(p.getX() + 1, p.getY());
      Point left = new Point(p.getX(), p.getY() - 1);
      Point right = new Point(p.getX(), p.getY() + 1);

      evaluatePoint(grid, shortestPath, queue, height, pathLen, up, exactStart);
      evaluatePoint(grid, shortestPath, queue, height, pathLen, down, exactStart);
      evaluatePoint(grid, shortestPath, queue, height, pathLen, left, exactStart);
      evaluatePoint(grid, shortestPath, queue, height, pathLen, right, exactStart);
    }

    return shortestPath.getOrDefault(end, Integer.MAX_VALUE);
  }

  private static void evaluatePoint(Map<Point, Integer> grid, Map<Point, Integer> shortestPath, List<Point> queue, int height, int pathLen, Point point,
      boolean exactStart) {
    if (grid.containsKey(point)) {
      int gridHeight = grid.get(point);
      if (gridHeight - height <= 1) {
        int currentLength = shortestPath.getOrDefault(point, Integer.MAX_VALUE);
        if (currentLength > pathLen + 1) {
          queue.add(point);
          shortestPath.put(point, (!exactStart && gridHeight == 0) ? 0 : pathLen + 1);
        }
      }
    }
  }

  private static void printGraph(Map<Point, Integer> grid, int numberOfRows, int numberOfColumns) {
    for (int row = 0; row < numberOfRows; row++) {
      for (int col = 0; col < numberOfColumns; col++) {
        char c = (char) (grid.get(new Point(row, col)) + 'a');
        System.out.print(c);
      }
      System.out.println();
    }
  }
}
