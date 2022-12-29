package org.aoc.tmousaw.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {
  private final Map<Vertex<T>, Set<Vertex<T>>> vertexToAdjacencyListMap;

  public Graph() {
    vertexToAdjacencyListMap = new HashMap<>();
  }

  public void addVertex(T data) {
    vertexToAdjacencyListMap.put(new Vertex<>(data), new HashSet<>());
  }

  public void removeVertex(T data) {
    Vertex<T> v = new Vertex<>(data);
    vertexToAdjacencyListMap.values().forEach(e -> e.remove(v));
    vertexToAdjacencyListMap.remove(v);
  }

  public void addEdge(T data1, T data2) {
    Vertex<T> v1 = new Vertex<>(data1);
    Vertex<T> v2 = new Vertex<>(data2);
    vertexToAdjacencyListMap.get(v1).add(v2);
    vertexToAdjacencyListMap.get(v2).add(v1);
  }

  public void removeEdge(T data1, T data2) {
    Vertex<T> v1 = new Vertex<>(data1);
    Vertex<T> v2 = new Vertex<>(data2);
    Set<Vertex<T>> edges1 = vertexToAdjacencyListMap.get(v1);
    Set<Vertex<T>> edges2 = vertexToAdjacencyListMap.get(v2);
    if (edges1 != null) {
      edges1.remove(v2);
    }
    if (edges2 != null) {
      edges2.remove(v1);
    }
  }

  public Vertex<T> getVertex(T data) {
    for (Vertex<T> v : vertexToAdjacencyListMap.keySet()) {
      if (v.getData().equals(data)) {
        return v;
      }
    }

    return null;
  }

  public Set<Vertex<T>> getAdjacencyList(Vertex<T> v) {
    return vertexToAdjacencyListMap.get(v);
  }

  public Map<Vertex<T>, Integer> dijkstra(Vertex<T> src) {
    Map<Vertex<T>, Integer> shortestPaths = new HashMap<>();
    Queue<Vertex<T>> queue = new LinkedList<>();
    Set<Vertex<T>> visited = new HashSet<>();

    for (Vertex<T> vertex : vertexToAdjacencyListMap.keySet()) {
      shortestPaths.put(vertex, Integer.MAX_VALUE);
    }
    shortestPaths.put(src, 0);

    queue.add(src);
    while (visited.size() < vertexToAdjacencyListMap.keySet().size()) {
      Vertex<T> v = queue.remove();

      visited.add(v);

      Set<Vertex<T>> adjacentVertices = vertexToAdjacencyListMap.get(v);
      for (Vertex<T> adjacent : adjacentVertices) {
        if (!visited.contains(adjacent)) {
          if (shortestPaths.get(v) + 1 < shortestPaths.get(adjacent)) {
            shortestPaths.put(adjacent, shortestPaths.get(v) + 1);
          }
          queue.add(adjacent);
        }
      }
    }

    return  shortestPaths;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Graph(\n");
    for (Vertex<T> vertex : vertexToAdjacencyListMap.keySet()) {
      builder.append("\t").append(vertex);
      if (vertexToAdjacencyListMap.get(vertex).size() > 0) {
        builder.append("\t{");
      }
      List<Vertex<T>> adjacencyList = new ArrayList<>(vertexToAdjacencyListMap.get(vertex));
      for (int i = 0; i < adjacencyList.size(); i++) {
        builder.append(adjacencyList.get(i));
        if (i < vertexToAdjacencyListMap.get(vertex).size() - 1) {
          builder.append(", ");
        } else {
          builder.append("}");
        }
      }
      builder.append("\n");
    }
    builder.append(")");
    return builder.toString();
  }
}
