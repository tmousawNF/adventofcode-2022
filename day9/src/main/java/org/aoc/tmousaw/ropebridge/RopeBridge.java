package org.aoc.tmousaw.ropebridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RopeBridge {

  public static void main(String[] args) throws IOException {
    int numKnots = 2;
    if (args.length > 0) {
      numKnots = Integer.parseInt(args[0]);
    }

    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    String line;
    Set<GridPosition> positions = new HashSet<>();
    List<GridPosition> knotPositions = new ArrayList<>();
    for (int i = 0; i < numKnots; i++) {
      knotPositions.add(new GridPosition());
    }
    GridPosition headPosition = knotPositions.get(0);
    GridPosition tailPosition = knotPositions.get(knotPositions.size() - 1);
    positions.add(new GridPosition(0, 0));
    while ((line = br.readLine()) != null) {
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
            GridPosition followingKnotPosition = knotPositions.get(i - 1);
            GridPosition knotPosition = knotPositions.get(i);
            if (!knotPosition.isTouching(followingKnotPosition)) {
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

                assert followingKnotPosition.getY() == knotPosition.getY();
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

                assert followingKnotPosition.getY() == knotPosition.getY();
              }
            }
          }

          // At this point, the tail is where it should visit, so add it to the set.
          positions.add(new GridPosition(tailPosition.getX(), tailPosition.getY()));

          iteration++;
        }
      }
    }

    System.out.println("Positions visited (numKnots=" + numKnots + "): " + positions.size());
  }
}
