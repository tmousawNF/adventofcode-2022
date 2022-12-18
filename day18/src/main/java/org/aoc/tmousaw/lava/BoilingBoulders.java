package org.aoc.tmousaw.lava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoilingBoulders {

  private static final Pattern p = Pattern.compile("^(\\d+),(\\d+),(\\d+)$");

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    String line;
    Grid3D grid3d = new Grid3D();
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        Matcher m = p.matcher(line.trim());
        m.find();
        int x, y, z;
        x = Integer.parseInt(m.group(1));
        y = Integer.parseInt(m.group(2));
        z = Integer.parseInt(m.group(3));
        grid3d.addPoint(new Lava(x, y, z));
      }
    }

    int sides = 0;
    Set<Point3D> points3dSetCopy = new HashSet<>(grid3d.getPoints());
    for (Point3D point3d : points3dSetCopy) {
      // Check left.
      if (!grid3d.getPoints().contains(new Point3D(point3d.getX() - 1, point3d.getY(), point3d.getZ()))) {
        sides++;
      }
      // Check right.
      if (!grid3d.getPoints().contains(new Point3D(point3d.getX() + 1, point3d.getY(), point3d.getZ()))) {
        sides++;
      }
      // Check down.
      if (!grid3d.getPoints().contains(new Point3D(point3d.getX(), point3d.getY() - 1, point3d.getZ()))) {
        sides++;
      }
      // Check up.
      if (!grid3d.getPoints().contains(new Point3D(point3d.getX(), point3d.getY() + 1, point3d.getZ()))) {
        sides++;
      }
      // Check backward.
      if (!grid3d.getPoints().contains(new Point3D(point3d.getX(), point3d.getY(), point3d.getZ() - 1))) {
        sides++;
      }
      // Check forward.
      if (!grid3d.getPoints().contains(new Point3D(point3d.getX(), point3d.getY(), point3d.getZ() - 1))) {
        sides++;
      }
    }
    System.out.println("Number of sides (Part 1): " + sides);

    sides = fillGridWithWater(grid3d);
    System.out.println("Number of sides (Part 2): " + sides);
  }

  private static int fillGridWithWater(Grid3D grid3D) {
    int minX = grid3D.getMinX() - 1;
    int maxX = grid3D.getMaxX() + 1;
    int minY = grid3D.getMinY() - 1;
    int maxY = grid3D.getMaxY() + 1;
    int minZ = grid3D.getMinZ() - 1;
    int maxZ = grid3D.getMaxZ() + 1;

    int sides = 0;
    Deque<Point3D> points = new ArrayDeque<>();
    points.add(new Point3D(0, 0, 0));
    while (!points.isEmpty()) {
      Point3D point3D = points.pop();
      if (grid3D.getPoints().contains(point3D)) {
        continue;
      } else {
        grid3D.addPoint(new Water(point3D.getX(), point3D.getY(), point3D.getZ()));
      }

      if (point3D.getX() - 1 >= minX) { // check left
        sides = checkPosition(grid3D, points, sides, point3D.getX() - 1, point3D.getY(), point3D.getZ());
      }
      if (point3D.getX() + 1 <= maxX) { // check right
        sides = checkPosition(grid3D, points, sides, point3D.getX() + 1, point3D.getY(), point3D.getZ());
      }
      if (point3D.getY() - 1 >= minY) { // check down
        sides = checkPosition(grid3D, points, sides, point3D.getX(), point3D.getY() - 1, point3D.getZ());
      }
      if (point3D.getY() + 1 <= maxY) { // check up
        sides = checkPosition(grid3D, points, sides, point3D.getX(), point3D.getY() + 1, point3D.getZ());
      }
      if (point3D.getZ() - 1 >= minZ) { // check backward
        sides = checkPosition(grid3D, points, sides, point3D.getX(), point3D.getY(), point3D.getZ() - 1);
      }
      if (point3D.getZ() + 1 <= maxZ) { // check backward
        sides = checkPosition(grid3D, points, sides, point3D.getX(), point3D.getY(), point3D.getZ() + 1);
      }
    }

    return sides;
  }

  public static int checkPosition(Grid3D grid3D, Deque<Point3D> points, int currentSum, int x, int y, int z) {
    Point3D p = new Point3D(x, y, z);
    int sum = currentSum;
    if (grid3D.getPoints().contains(p)) {
      Point3D tmp = grid3D.getPoint(p);
      if (tmp instanceof Lava) {
        sum++;
      }
    } else {
      points.add(p);
    }

    return sum;
  }
}
