package org.aoc.tmousaw.day16;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.graph.Graph;
import org.aoc.tmousaw.graph.Vertex;

public class Volcanium extends AdventOfCodeSolver {

  public Volcanium() throws IOException {
    this("input.txt");
  }

  public Volcanium(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  private static final Pattern p = Pattern.compile("^Valve (\\p{Upper}+).*?rate=(\\d+).*?valves? (.*)$");

  public static void main(String[] args) throws IOException {
    Volcanium volcanium = new Volcanium();
    volcanium.solve();
    volcanium.printAnswers();
    System.out.println();
    volcanium.printTimings();
  }

  @Override
  public void solve() {
    Graph<Valve> graph = new Graph<>();
    Map<String, Valve> labelToValveMap = new HashMap<>();

    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        Matcher m = p.matcher(line.trim());
        m.find();
        String label = m.group(1);
        int rate = Integer.parseInt(m.group(2));
        Valve v = new Valve(label, rate);
        graph.addVertex(v);
        labelToValveMap.put(label, v);
      }
    }

    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        Matcher m = p.matcher(line.trim());
        m.find();
        String label = m.group(1);
        assert labelToValveMap.containsKey(label);
        Valve v1 = labelToValveMap.get(label);
        String[] tokens = m.group(3).trim().split(",");
        for (String adjacentLabel : tokens) {
          assert labelToValveMap.containsKey(adjacentLabel.trim());
          Valve v2 = labelToValveMap.get(adjacentLabel.trim());
          graph.addEdge(v1, v2);
        }
      }
    }

    System.out.println(graph);
    Map<Vertex<Valve>, Integer> vertexIntegerMap = graph.dijkstra(graph.getVertex(labelToValveMap.get("AA")));
    System.out.println(vertexIntegerMap);

    Map<Info, Integer> cacheIntegerMap = new HashMap<>();
    Set<Valve> openedValves = new HashSet<>();
    Set<Vertex<Valve>> visitedValves = new HashSet<>();
    int optimizePressureRelease = findOptimalPathToReleasePressure(graph, graph.getVertex(labelToValveMap.get("AA")), 30, cacheIntegerMap, openedValves, 0,
        visitedValves);
    System.out.println(optimizePressureRelease);
  }

  private static int findOptimalPathToReleasePressure(Graph<Valve> graph, Vertex<Valve> current, int numMins, Map<Info, Integer> cacheIntegerMap,
      Set<Valve> openedValves, int currentReleaseRate, Set<Vertex<Valve>> visitedValves) {
    if (numMins == 0) {
      return 0;
    }

    visitedValves.add(current);
    Info info = new Info(current, openedValves, currentReleaseRate);
    if (cacheIntegerMap.containsKey(info)) {
      return cacheIntegerMap.get(info);
    }

    int released = 0;
    if (!openedValves.contains(current.getData()) && current.getData().getFlowRate() > 0) {
      Set<Valve> newOpenedValves = new HashSet<>(openedValves);
      newOpenedValves.add(current.getData());
      released = findOptimalPathToReleasePressure(graph, current, numMins - 1, cacheIntegerMap, newOpenedValves,
          currentReleaseRate += current.getData().getFlowRate(), visitedValves);
    }

    for (Vertex<Valve> v : graph.getAdjacencyList(current)) {
      if (!visitedValves.contains(v)) {
        int tmpReleased = findOptimalPathToReleasePressure(graph, v, numMins - 1, cacheIntegerMap, openedValves, currentReleaseRate, visitedValves);
        released = Math.max(released, tmpReleased);
      }
    }

    released += currentReleaseRate;
    cacheIntegerMap.put(new Info(current, openedValves, currentReleaseRate), released);
    return released;
  }
}
