package org.aoc.tmousaw.day24;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.geometry.Point;

public class BlizzardMaze extends AdventOfCodeSolver {

  private final Valley valley;

  public BlizzardMaze() throws IOException {
    this("input.txt");
  }

  public BlizzardMaze(String resourceFileName) throws IOException {
    super(resourceFileName);
    valley = new Valley();
  }

  public static void main(String[] args) throws IOException {
    BlizzardMaze blizzardMaze = new BlizzardMaze();
    blizzardMaze.solve();
    blizzardMaze.printAnswers();
    System.out.println();
    blizzardMaze.printTimings();
  }

  @Override
  public void solve() {
    for (int y = 0; y < getLinesOfInput().size(); y++) {
      String line = getLinesOfInput().get(y);
      for (int x = 0; x < line.length(); x++) {
        char c = line.charAt(x);
        if (c == '#') {
          valley.addPoint(new Wall(x, y));
        } else if (c != '.') {
          valley.addPoint(new Blizzard(x, y, c));
        }
      }
    }

    Expedition expedition = new Expedition(1, 0);
    valley.addPoint(expedition);

    int moves = findPath(new State(expedition, 0, false, false));
    addAnswer("Number of moves", moves);
  }

  private int findPath(State state) {
    final Deque<State> queue = new ArrayDeque<>();
    final Set<State> cache = new HashSet<>();
    queue.push(state);
    while (!queue.isEmpty()) {
      State s = queue.removeLast();

      int currentX = s.getExpedition().getX();
      int currentY = s.getExpedition().getY();
      if (currentX < 0 || currentX > valley.getMaxX() || currentY < 0 || currentY > valley.getMaxY() || valley.getWalls().contains(s.getExpedition())) {
        // Off grid or into wall
        continue;
      }

      if (currentX == valley.getMaxX() - 1 && currentY == valley.getMaxY()) {
        if (!s.isEndVisited()) {
          // Reached end.
          addAnswer("Number of  moves", s.getMinute()); // Part 1
          queue.clear();
          queue.add(new State(s.getExpedition(), s.getMinute(), s.isStartVisited(), true));
          continue;
        }
        else if (s.isStartVisited()) {
          return s.getMinute();
        }
      } else if (currentX == 1 && currentY == 0 && s.isEndVisited()) {
        if (!s.isStartVisited()) {
          // Reached the start
          queue.clear();
          queue.add(new State(s.getExpedition(), s.getMinute(), true, s.isEndVisited()));
          continue;
        }
      }

      if (cache.contains(s)) {
        continue; // Already checked state.
      }

      cache.add(s);
      List<Point> blizzards = valley.getBlizzards(s.getMinute() + 1);

      if (!blizzards.contains(s.getExpedition())) {
        queue.push(new State(s.getExpedition(), s.getMinute() + 1, s.isStartVisited(), s.isEndVisited())); // Wait
      }

      final Expedition north = new Expedition(s.getExpedition().getX(), s.getExpedition().getY() - 1);
      if (!blizzards.contains(north)) {
        queue.push(new State(north, s.getMinute() + 1, s.isStartVisited(), s.isEndVisited()));
      }
      final Expedition south = new Expedition(s.getExpedition().getX(), s.getExpedition().getY() + 1);
      if (!blizzards.contains(south)) {
        queue.push(new State(south, s.getMinute() + 1, s.isStartVisited(), s.isEndVisited()));
      }
      final Expedition east = new Expedition(s.getExpedition().getX() + 1, s.getExpedition().getY());
      if (!blizzards.contains(east)) {
        queue.push(new State(east, s.getMinute() + 1, s.isStartVisited(), s.isEndVisited()));
      }
      final Expedition west = new Expedition(s.getExpedition().getX() - 1, s.getExpedition().getY());
      if (!blizzards.contains(west)) {
        queue.push(new State(west, s.getMinute() + 1, s.isStartVisited(), s.isEndVisited()));
      }
    }

    return -1;
  }

  public void printMaze() {
    for (int y = valley.getMinY(); y <= valley.getMaxY(); y++) {
      for (int x = valley.getMinX(); x <= valley.getMaxX(); x++) {
        Point p = valley.getPointsMap().get(new Point(x, y));
        if (p == null) {
          System.out.print(".");
        } else if (p instanceof Wall) {
          System.out.print("#");
        } else {
          List<Point> blizzards = valley.getPoints().stream().filter(b -> b.equals(p)).collect(Collectors.toList());
          if (blizzards.size() > 1) {
            System.out.print(blizzards.size());
          } else {
            System.out.print(((Blizzard) blizzards.stream().findFirst().orElseThrow()).getDirection().getDir());
          }
        }
      }
      System.out.println();
    }
  }

  public void printMazeOverTime() {
    int maxMinute = Integer.MIN_VALUE;
    for (int minute : valley.getMinuteToBlizzardMap().keySet()) {
      if (minute > maxMinute) {
        maxMinute = minute;
      }
    }

    for (int i = 1; i <= maxMinute; i++) {
      List<Point> walls = valley.getWalls();
      List<Point> blizzards = valley.getBlizzards(i);

      System.out.println("=========");
      System.out.println("MINUTE " + i);
      System.out.println("=========");
      for (int y = valley.getMinY(); y <= valley.getMaxY(); y++) {
        for (int x = valley.getMinX(); x <= valley.getMaxX(); x++) {
          Point p = new Point(x, y);
          if (walls.contains(p)) {
            System.out.print("#");
          } else if (blizzards.contains(p)) {
            int numBlizzards = Collections.frequency(blizzards, p);
            if (numBlizzards == 1) {
              Point point = blizzards.stream().filter(p::equals).findFirst().orElseThrow();
              System.out.print(((Blizzard)point).getDirection().getDir());
            } else {
              System.out.print(numBlizzards);
            }
          } else {
            System.out.print(".");
          }
        }
        System.out.println();
      }
      System.out.println();
    }
  }
}
