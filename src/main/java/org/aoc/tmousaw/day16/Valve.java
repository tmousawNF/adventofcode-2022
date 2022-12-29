package org.aoc.tmousaw.day16;

public class Valve {

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

  public void open() {
    this.state = State.OPEN;
  }

  public int getFlowRate() {
    return flowRate;
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
    return valve.label.equals(label) && valve.flowRate.equals(flowRate);
  }

  @Override
  public final int hashCode() {
    int prime = 31;
    int result = 17;
    result = prime * result + label.hashCode();
    result = prime * result + flowRate.hashCode();

    return result;
  }

  @Override
  public String toString() {
    return String.format("Valve(%s, %d)", label, flowRate);
  }
}
