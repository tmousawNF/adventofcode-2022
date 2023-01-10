package org.aoc.tmousaw.day16;

import java.util.Objects;

public class Valve implements Comparable<Valve> {

  private enum State {
    CLOSED,
    OPEN
  }

  private final String label;
  private final Integer flowRate;
  private State state;

  public Valve(String label, int flowRate) {
    this.label = label;
    this.flowRate = flowRate;
    this.state = State.CLOSED;
  }

  public Valve(Valve valve) {
    label = valve.label;
    flowRate = valve.flowRate;
    state = valve.state;
  }

  public void open() {
    this.state = State.OPEN;
  }

  public int getFlowRate() {
    return flowRate;
  }

  public boolean isOpen() {
    return State.OPEN.equals(state);
  }

  public String getLabel() {
    return label;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Valve valve = (Valve) o;
    return Objects.equals(label, valve.label) && Objects.equals(flowRate, valve.flowRate) && state == valve.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, flowRate, state);
  }

  @Override
  public String toString() {
    return String.format("Valve(%s, %d, %s)", label, flowRate, state);
  }


  @Override
  public int compareTo(Valve valve) {
    return valve.flowRate - flowRate;
  }
}
