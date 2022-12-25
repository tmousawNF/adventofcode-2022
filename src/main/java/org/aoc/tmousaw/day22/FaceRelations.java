package org.aoc.tmousaw.day22;

import java.util.List;
import java.util.Map;

public class FaceRelations {

  private static final Map<Face, List<Face>> faceToAdjacentFaceListMap = Map.of(
      Face.FRONT, List.of(Face.RIGHT, Face.BOTTOM, Face.LEFT, Face.TOP),
      Face.RIGHT, List.of(Face.BACK, Face.BOTTOM, Face.FRONT, Face.TOP),
      Face.BACK, List.of(Face.LEFT, Face.BOTTOM, Face.RIGHT, Face.TOP),
      Face.LEFT, List.of(Face.FRONT, Face.BOTTOM, Face.BACK, Face.TOP),
      Face.TOP, List.of(Face.RIGHT, Face.FRONT, Face.LEFT, Face.BACK),
      Face.BOTTOM, List.of(Face.LEFT, Face.FRONT, Face.RIGHT, Face.BACK)
  );

  public static Face getResultingFace(Face currentFace, Orientation direction) {
    return faceToAdjacentFaceListMap.get(currentFace).get(direction.getIndex());
  }
}
