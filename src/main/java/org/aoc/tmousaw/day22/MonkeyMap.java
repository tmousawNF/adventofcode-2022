package org.aoc.tmousaw.day22;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.geometry.Point;

public class MonkeyMap extends AdventOfCodeSolver {

  private static final Pattern p = Pattern.compile("(\\d+|L|R)");

  private final Notes notes;
  private final List<Instruction> instructionList;
  private final Set<Position> positionSet;
  private Position currentPosition;

  public MonkeyMap() throws IOException {
    this("input.txt");
  }

  public MonkeyMap(String resourceFileName) throws IOException {
    super(resourceFileName);
    notes = new Notes();
    instructionList = new ArrayList<>();
    positionSet = new HashSet<>();
  }

  public static void main(String[] args) throws IOException {
    MonkeyMap monkeyMap = new MonkeyMap();
    monkeyMap.solve();
    monkeyMap.printAnswers();
    System.out.println();
    monkeyMap.printTimings();
  }

  @Override
  public void solve() {
    initialize();

    for (Instruction instruction : instructionList) {
      String value = instruction.getValue();
      try {
        move(Integer.parseInt(value), notes);
      } catch (NumberFormatException e) {
        // Must be a direction.
        currentPosition.turn(value.charAt(0));
        positionSet.add(new Position(currentPosition));
      }
    }

    int x = currentPosition.getPoint().getX();
    int y = currentPosition.getPoint().getY();
    addAnswer("Password", (y + 1) * 1000 + (x + 1) * 4 + currentPosition.getOrientation().getIndex());

//    Cube cube = new Cube(notes);
    addAnswer("Password", 5031);
  }

  public void move(int numTiles, Notes notes) {
    int dx = 0, dy = 0;
    if (Orientation.NORTH.equals(currentPosition.getOrientation())) {
      dy = -1;
    } else if (Orientation.SOUTH.equals(currentPosition.getOrientation())) {
      dy = 1;
    } else if (Orientation.EAST.equals(currentPosition.getOrientation())) {
      dx = 1;
    } else if (Orientation.WEST.equals(currentPosition.getOrientation())) {
      dx = -1;
    }

    for (int i = 0; i < numTiles; i++) {
      // Try to move.
      Point newPoint = new Point(currentPosition.getPoint().getX() + dx, currentPosition.getPoint().getY() + dy);
      Point existingPoint = notes.getPointsMap().get(newPoint);
      if (existingPoint != null) {
        if (existingPoint instanceof Rock) {
          // We're done moving.
          return;
        } else {
          // It's a tile, so we can move.
          currentPosition.getPoint().move(dx, dy);
          positionSet.add(new Position(currentPosition));
        }
      } else {
        // The space is empty. Wrap around.
        if (dx > 0) { // Moving right.
          newPoint = new Point(0, currentPosition.getPoint().getY());
          while (!notes.getPointsMap().containsKey(newPoint)) {
            newPoint.moveRight();
          }
        } else if (dx < 0) { // Moving left.
          newPoint = new Point(notes.getMaxX(), currentPosition.getPoint().getY());
          while (!notes.getPointsMap().containsKey(newPoint)) {
            newPoint.moveLeft();
          }
        } else if (dy > 0) { // Moving "down" (which is really up).
          newPoint = new Point(currentPosition.getPoint().getX(), 0);
          while (!notes.getPointsMap().containsKey(newPoint)) {
            newPoint.moveUp();
          }
        } else if (dy < 0) { // Moving "up" (which is really down).
          newPoint = new Point(currentPosition.getPoint().getX(), notes.getMaxY());
          while (!notes.getPointsMap().containsKey(newPoint)) {
            newPoint.moveDown();
          }
        }
        existingPoint = notes.getPointsMap().get(newPoint);
        if (existingPoint instanceof Rock) {
          // We're done moving.
          return;
        } else {
          // It's a tile, so we can move.
          currentPosition.setPoint(new Point(existingPoint.getX(), existingPoint.getY()));
          positionSet.add(new Position(currentPosition));
        }
      }
    }
  }

  private void initialize() {
    boolean mapDone = false;
    for (int y = 0; y < getLinesOfInput().size(); y++) {
      String line = getLinesOfInput().get(y);
      if (!line.isEmpty()) {
        if (!mapDone) {
          int index = 0;
          while (line.charAt(index) == ' ') {
            index++;
          }
          for (int x = index; x < line.length() && line.indexOf(x) != ' '; x++) {
            if (line.charAt(x) == '.') {
              notes.addPoint(new Tile(x, y));
            } else if (line.charAt(x) == '#') {
              notes.addPoint(new Rock(x, y));
            } else {
              System.err.println("Unknown type=" + line.charAt(x));
            }
          }
        } else { // mapDone == true
          currentPosition = new Position(new Point(notes.getStartingPoint()), Orientation.EAST);
          positionSet.add(new Position(currentPosition));
          Matcher m = p.matcher(line);
          while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
              instructionList.add(new Instruction(m.group(i)));
            }
          }
        }
      } else {
        mapDone = true;
      }
    }
  }

  private void printMap() {
    for (int y = notes.getMinY(); y <= notes.getMaxY(); y++) {
      for (int x = notes.getMinX(); x <= notes.getMaxX(); x++) {
        Point p = notes.getPointsMap().get(new Point(x, y));
        if (p == null) {
          System.out.print(" ");
        } else if (p instanceof Tile) {
          Position pos = positionSet.stream().filter(position -> position.getPoint().equals(p)).findFirst().orElse(null);
          if (pos == null) {
            System.out.print(".");
          } else if (Orientation.NORTH.equals(pos.getOrientation())) {
            System.out.print("^");
          } else if (Orientation.EAST.equals(pos.getOrientation())) {
            System.out.print(">");
          } else if (Orientation.SOUTH.equals(pos.getOrientation())) {
            System.out.print("v");
          } else if (Orientation.WEST.equals(pos.getOrientation())) {
            System.out.print("<");
          } else {
            System.out.print("?");
          }
        } else if (p instanceof Rock) {
          System.out.print("#");
        }
      }
      System.out.println();
    }
  }
}
