package org.aoc.tmousaw.day22;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.aoc.tmousaw.geometry.Grid3D;
import org.aoc.tmousaw.geometry.Point;
import org.aoc.tmousaw.geometry.Point3D;

public class Cube extends Grid3D {

  private final int pointsPerFace;
  private final int pointsPerEdge;
  private final Map<Point, Point3D> pointTo3DPointMap;
  private final Map<Point3D, Point> point3DToPointMap;

  /**
   * Given the <tt>notes</tt>, populate the 3D grid. The starting position is considered the top leftmost point in the cube.
   *
   * @param notes the notes from the monkeys
   */
  public Cube(Notes notes) {
    pointsPerFace = notes.getPoints().size() / 6;
    pointsPerEdge = (int) Math.sqrt(pointsPerFace);

    Point startingPoint = notes.getStartingPoint();
    pointTo3DPointMap = new HashMap<>();
    point3DToPointMap = new HashMap<>();
    // Now fill in the back face.
    for (int y = 0; y < pointsPerEdge; y++) {
      for (int x = 0; x < pointsPerEdge; x++) {
        CubePoint cubePoint = new CubePoint(x, y, 0, Face.BACK);
        addPoint(cubePoint);
        Point p = new Point(startingPoint.getX() + x, startingPoint.getY() + y);
        pointTo3DPointMap.put(notes.getPointsMap().get(p), cubePoint);
        point3DToPointMap.put(cubePoint, notes.getPointsMap().get(p));
      }
    }

    Point currentPoint = startingPoint;
    Face face = Face.BACK;
    while (currentPoint != null) {
      // Now check to the right of this cube face to see if there is another definition in the same line.
      currentPoint = new Point(currentPoint.getX() + pointsPerEdge, currentPoint.getY());
      if (notes.getPoints().contains(currentPoint)) {
        face = FaceRelations.getResultingFace(face, Orientation.WEST);
        for (int y = 0; y < pointsPerEdge; y++) {
          for (int x = 0; x < pointsPerEdge; x++) {
            CubePoint cubePoint = new CubePoint(pointsPerEdge, y, x, face);
            addPoint(cubePoint);
            Point p = new Point(currentPoint.getX() + x, currentPoint.getY() + y);
            pointTo3DPointMap.put(notes.getPointsMap().get(p), cubePoint);
            point3DToPointMap.put(cubePoint, notes.getPointsMap().get(p));
          }
        }
        currentPoint = new Point(currentPoint.getX() + pointsPerEdge, currentPoint.getY());
      }
    }
  }

  class CubePoint extends Point3D {

    private final Face face;

    public CubePoint(int x, int y, int z, Face face) {
      super(x, y, z);
      this.face = face;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      CubePoint cubePoint = (CubePoint) o;
      return face == cubePoint.face;
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), face);
    }
  }
}
