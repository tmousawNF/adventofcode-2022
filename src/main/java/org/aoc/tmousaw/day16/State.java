package org.aoc.tmousaw.day16;

import java.util.List;
import java.util.Objects;
import org.aoc.tmousaw.graph.Vertex;

public class State {

  private final Vertex<Valve> currentValve;
  private final int minutesRemaining;
  private final List<Valve> valves;
  private final int releasedPressure;
  private final int helpers;

  public State(Vertex<Valve> currentValve, int minutesRemaining, List<Valve> valves, int releasedPressure, int helpers) {
    this.currentValve = currentValve;
    this.minutesRemaining = minutesRemaining;
    this.valves = valves;
    this.releasedPressure = releasedPressure;
    this.helpers = helpers;
  }

  public Vertex<Valve> getCurrentValve() {
    return currentValve;
  }

  public int getMinutesRemaining() {
    return minutesRemaining;
  }

  public List<Valve> getValves() {
    return valves;
  }

  public int getReleasedPressure() {
    return releasedPressure;
  }

  public int getHelpers() {
    return helpers;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    State state = (State) o;
    return minutesRemaining == state.minutesRemaining && releasedPressure == state.releasedPressure && helpers == state.helpers && Objects.equals(
        currentValve, state.currentValve) && Objects.equals(valves, state.valves);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentValve, minutesRemaining, valves, releasedPressure, helpers);
  }

  @Override
  public String toString() {
    return String.format("%s, %d minsRemaing, %s, %d pressureReleased", currentValve, minutesRemaining, valves, releasedPressure);
  }
}
