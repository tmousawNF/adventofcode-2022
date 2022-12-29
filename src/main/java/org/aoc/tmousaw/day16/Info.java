package org.aoc.tmousaw.day16;

import java.util.Set;
import org.aoc.tmousaw.graph.Vertex;

public class Info {
  public final Vertex<Valve> vertex;
  public final Set<Valve> openedValves;
  public final int totalReleaseRate;

  public Info(Vertex<Valve> vertex, Set<Valve> openedValves, int totalReleaseRate) {
    this.vertex = vertex;
    this.openedValves = openedValves;
    this.totalReleaseRate = totalReleaseRate;
  }
}
