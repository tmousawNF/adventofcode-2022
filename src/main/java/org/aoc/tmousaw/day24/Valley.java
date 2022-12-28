package org.aoc.tmousaw.day24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.aoc.tmousaw.geometry.Grid;
import org.aoc.tmousaw.geometry.Point;

public class Valley extends Grid {

  private final Map<Integer, List<Point>> minuteToBlizzardMap;

  public Valley() {
    minuteToBlizzardMap = new HashMap<>();
  }

  public List<Point> getWalls() {
    return getPoints().stream().filter(p -> p instanceof Wall).collect(Collectors.toList());
  }

  public void moveBlizzards(int minute) {
    if (minuteToBlizzardMap.containsKey(minute)) {
      return; // Already have it in the map.
    }

    int closestMinute = 0;
    List<Point> blizzards;
    if (!minuteToBlizzardMap.isEmpty()) {
      for (int min : minuteToBlizzardMap.keySet()) {
        if (min < minute && min > closestMinute) {
          closestMinute = min;
        }
      }

      blizzards = minuteToBlizzardMap.get(closestMinute);
    } else {
      blizzards = getPoints().stream().filter(p -> p instanceof Blizzard).collect(Collectors.toList());
    }

    int finalClosestMinute = closestMinute;
    final List<Point> newBlizzards =  new ArrayList<>();
    blizzards.forEach(p -> {
      Blizzard b = new Blizzard(p.getX(), p.getY(), ((Blizzard) p).getDirection().getDir());
      for (int i = finalClosestMinute; i < minute; i++) {
        if (Direction.NORTH.equals(b.getDirection())) {
          if (getWalls().contains(new Point(b.getX(), b.getY() - 1))) {
            b.move(0, getMaxY() - 2);
          } else {
            b.move(0, -1);
          }
        } else if (Direction.SOUTH.equals(b.getDirection())) {
          if (getWalls().contains(new Point(b.getX(), b.getY() + 1))) {
            b.move(0, 1 - b.getY());
          } else {
            b.move(0, 1);
          }
        } else if (Direction.EAST.equals(b.getDirection())) {
          if (getWalls().contains(new Point(b.getX() + 1, b.getY()))) {
            b.move(1 - b.getX(), 0);
          } else {
            b.move(1, 0);
          }
        } else if (Direction.WEST.equals(b.getDirection())) {
          if (getWalls().contains(new Point(b.getX() - 1, b.getY()))) {
            b.move(getMaxX() - 2, 0);
          } else {
            b.move(-1, 0);
          }
        }
      }
      newBlizzards.add(b);
    });

    minuteToBlizzardMap.put(minute, newBlizzards);
  }

  public List<Point> getBlizzards(int minute) {
    if (!minuteToBlizzardMap.containsKey(minute)) {
      moveBlizzards(minute);
    }

    return minuteToBlizzardMap.get(minute);
  }

  public Map<Integer, List<Point>> getMinuteToBlizzardMap() {
    return minuteToBlizzardMap;
  }
}
