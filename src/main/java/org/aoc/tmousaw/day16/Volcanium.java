package org.aoc.tmousaw.day16;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.graph.Graph;
import org.aoc.tmousaw.graph.Vertex;

public class Volcanium extends AdventOfCodeSolver {

  private static final Pattern p = Pattern.compile("^Valve (\\p{Upper}+).*?rate=(\\d+).*?valves? (.*)$");
  private final Graph<Valve> graph;
  private final Map<String, Valve> labelToValveMap;

  public Volcanium() throws IOException {
    this("input.txt");
  }

  public Volcanium(String resourceFileName) throws IOException {
    super(resourceFileName);
    graph = new Graph<>();
    labelToValveMap = new HashMap<>();
  }

  public static void main(String[] args) throws IOException {
    Volcanium volcanium = new Volcanium();
    volcanium.solve();
    volcanium.printAnswers();
    System.out.println();
    volcanium.printTimings();
  }

  @Override
  public void solve() {
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

    System.out.println("Starting Part 1");
    addAnswer("Pressure released", calculateMaxPressure(0));
    System.out.println("Starting Part 2");
    addAnswer("Pressure released", calculateMaxPressure(1));
  }

  private int calculateMaxPressure(int helpers) {
    final Deque<State> queue = new ArrayDeque<>();
    final Set<State> cache = new HashSet<>();
    int max = 0;

//    int iterations = 0;
    int minutesRemaining = (helpers > 0) ? 26 : 30;
    queue.push(new State(graph.getVertex(labelToValveMap.get("AA")), minutesRemaining,
        copyValves(graph.getAllData().stream().filter(v -> v.getFlowRate() > 0).collect(Collectors.toList())), 0, helpers));
    while (!queue.isEmpty()) {
      State s = queue.removeLast();

//      if (iterations++ % 100000 == 0) {
//        System.out.println(s);
//      }

      if (cache.contains(s)) {
        // Already processed.
        continue;
      }

      cache.add(s);

      // If there are no more valves that are closed, stop iterating.
      if (s.getValves().stream().filter(Valve::isClosed).findAny().isEmpty()) {
        max = Math.max(max, s.getReleasedPressure());
        continue;
      }

      if (s.getMinutesRemaining() == 0) {
        if (s.getHelpers() > 0) {
          queue.push(
              new State(graph.getVertex(labelToValveMap.get("AA")), minutesRemaining, copyValves(s.getValves()), s.getReleasedPressure(), s.getHelpers() - 1));
        } else {
          max = Math.max(max, s.getReleasedPressure());
        }
        continue;
      }

      // Here I was trying to filter out any state where the same set of valves are open at the same time but has a lower released pressure. But it doesn't work.
//      if (cache.stream()
//          .filter(c -> c.getValves().containsAll(s.getValves()))
//          .filter(c -> c.getMinutesRemaining() == s.getMinutesRemaining())
//          .filter(c -> c.getHelpers() == s.getHelpers())
//          .filter(c -> c.getCurrentValve().equals(s.getCurrentValve()))
//          .filter(c -> c.getReleasedPressure() > s.getReleasedPressure()).findAny().isPresent()) {
//        // There is already the same situation with more released pressure, so this path can be terminated.
//        System.out.println("Skipping " + s);
//        continue;
//      }

      if (maxPressureThatCanBeReleased(s) < max) {
        continue;
      }

      Vertex<Valve> vertex = s.getCurrentValve();
      List<Valve> valves = copyValves(s.getValves());
      Valve current = valves.stream().filter(v -> v.getLabel().equals(vertex.getData().getLabel())).findFirst().orElse(null);
      if (current != null && current.isClosed()) {
        int releasedPressure = s.getReleasedPressure();
        valves.stream().filter(v -> v.getLabel().equals(current.getLabel())).forEach(Valve::open);
        queue.add(
            new State(vertex, s.getMinutesRemaining() - 1, valves, releasedPressure + (s.getMinutesRemaining() - 1) * current.getFlowRate(), s.getHelpers()));
      }

      for (Vertex<Valve> v : graph.getAdjacencyList(vertex)) {
        valves = copyValves(s.getValves());
        queue.add(new State(v, s.getMinutesRemaining() - 1, valves, s.getReleasedPressure(), s.getHelpers()));
      }
    }

    return max;
  }

  private int maxPressureThatCanBeReleased(State state) {
    List<Valve> closedValves = state.getValves().stream().filter(Valve::isClosed).sorted().collect(Collectors.toList());

    int helperMinutesRemaining = 25;
    int minutesRemaining = state.getMinutesRemaining() - 1;
    int pressureThatCanBeReleased = state.getReleasedPressure();

    int i = 0;
    if (state.getHelpers() > 0) {
      while (i < closedValves.size() && helperMinutesRemaining > minutesRemaining) {
        for (int j = 0; j < state.getHelpers(); j++) {
          pressureThatCanBeReleased += helperMinutesRemaining * closedValves.get(i).getFlowRate();
        }
        helperMinutesRemaining--;
        i++;
      }
    }

    // At this point, either we have processed all valves or helperMinutesRemaining equals minutesRemaining.
    while (i < closedValves.size()) {
      pressureThatCanBeReleased += minutesRemaining * closedValves.get(i).getFlowRate();
      for (int j = 0; j < state.getHelpers(); j++) {
        i++;
        if (i < closedValves.size()) {
          pressureThatCanBeReleased += minutesRemaining * closedValves.get(i).getFlowRate();
        } else {
          break;
        }
      }
      minutesRemaining--;
      i++;
    }

    return pressureThatCanBeReleased;
  }

  private List<Valve> copyValves(List<Valve> valves) {
    List<Valve> copy = new ArrayList<>();

    for (Valve v : valves) {
      copy.add(new Valve(v));
    }

    return copy;
  }
}
