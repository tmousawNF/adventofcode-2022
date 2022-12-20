package org.aoc.tmousaw.day9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.geometry.Point;

public class RopeBridge extends AdventOfCodeSolver {

  public RopeBridge() throws IOException {
    super();
  }

  public RopeBridge(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    RopeBridge ropeBridge = new RopeBridge();
    ropeBridge.solve();
    ropeBridge.printAnswers();
    System.out.println();
    ropeBridge.printTimings();
  }

  @Override
  public void solve() {
    simulate(2);
    simulate(10);
  }

  public void simulate(int numKnots) {
    Set<Point> positions = new HashSet<>();
    List<Point> knotPositions = new ArrayList<>();
    int leftmost = 0, rightmost = 0, upmost = 0, downmost = 0;

    for (int i = 0; i < numKnots; i++) {
      knotPositions.add(new Point());
    }
    Point headPosition = knotPositions.get(0);
    Point tailPosition = knotPositions.get(knotPositions.size() - 1);
    positions.add(new Point(0, 0));
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        String input = line.trim();
        String[] tokens = input.split(" ");

        assert tokens[0].length() == 1;
        int numIterations = Integer.parseInt(tokens[1]);
        String direction = tokens[0];
        int iteration = 0;
        while (iteration < numIterations) {
          if ("R".equals(direction)) {
            headPosition.moveRight();
          } else if ("L".equals(direction)) {
            headPosition.moveLeft();
          } else if ("U".equals(direction)) {
            headPosition.moveUp();
          } else if ("D".equals(direction)) {
            headPosition.moveDown();
          } else {
            throw new IllegalArgumentException("Unknown direction (" + direction + ")");
          }

          for (int i = 1; i < knotPositions.size(); i++) {
            // Now determine if the next knot needs to move.
            Point followingKnotPosition = knotPositions.get(i - 1);
            Point knotPosition = knotPositions.get(i);
            if (!knotPosition.isAdjacent(followingKnotPosition)) {
              // The first move is always in the direction that is more than one position away.
              if (Math.abs(followingKnotPosition.getX() - knotPosition.getX()) > 1) {
                // Move right or left as appropriate.
                if (followingKnotPosition.getX() > knotPosition.getX()) {
                  knotPosition.moveRight();
                } else {
                  knotPosition.moveLeft();
                }

                // If the tail moves left or right, it always has to be in the same row.
                if (followingKnotPosition.getY() != knotPosition.getY()) {
                  if (followingKnotPosition.getY() > knotPosition.getY()) {
                    knotPosition.moveUp();
                  } else {
                    knotPosition.moveDown();
                  }
                }
              } else {
                // It is the y position that is more than 1 away.
                if (followingKnotPosition.getY() > knotPosition.getY()) {
                  knotPosition.moveUp();
                } else {
                  knotPosition.moveDown();
                }

                // If the tails move up or down, it always has to be in the same column.
                if (followingKnotPosition.getX() != knotPosition.getX()) {
                  if (followingKnotPosition.getX() > knotPosition.getX()) {
                    knotPosition.moveRight();
                  } else {
                    knotPosition.moveLeft();
                  }
                }
              }
              assert followingKnotPosition.isAdjacent(knotPosition);
            }
          }

          // At this point, the tail is where it should visit, so add it to the set.
          positions.add(new Point(tailPosition.getX(), tailPosition.getY()));

          iteration++;
        }
        // After all iterations, see if the leftmost, rightmost, upmost, or downmost values need to change.
        if (headPosition.getX() < leftmost) {
          leftmost = headPosition.getX();
        } else if (headPosition.getX() > rightmost) {
          rightmost = headPosition.getX();
        } else if (headPosition.getY() < downmost) {
          downmost = headPosition.getY();
        } else if (headPosition.getY() > upmost) {
          upmost = headPosition.getY();
        }
      }
    }

    // printPositions(positions, leftmost, rightmost, upmost, downmost);
    addAnswer("Positions visited", positions.size());
  }

  private static void printPositions(Set<Point> positions, int leftmost, int rightmost, int upmost, int downmost) {
    for (int y = upmost; y >= downmost; y--) {
      for (int x = leftmost; x <= rightmost; x++) {
        if (positions.contains(new Point(x, y))) {
          System.out.print("#");
        } else {
            System.out.print(".");
        }
      }

      System.out.println();
    }
  }
}
