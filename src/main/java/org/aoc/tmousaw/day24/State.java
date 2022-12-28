package org.aoc.tmousaw.day24;

import java.util.Objects;

public class State {
  private final Expedition expedition;
  private final int minute;
  private final boolean startVisited;
  private final boolean endVisited;

  public State(Expedition expedition, int minute, boolean startVisited, boolean endVisited) {
    this.expedition = expedition;
    this.minute = minute;
    this.startVisited = startVisited;
    this.endVisited = endVisited;
  }

  public Expedition getExpedition() {
    return expedition;
  }

  public int getMinute() {
    return minute;
  }

  public boolean isStartVisited() {
    return startVisited;
  }

  public boolean isEndVisited() {
    return endVisited;
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
    return minute == state.minute && startVisited == state.startVisited && endVisited == state.endVisited && Objects.equals(expedition,
        state.expedition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expedition, minute, startVisited, endVisited);
  }

  @Override
  public String toString() {
    return String.format("Expedition {%s}, minute (%d), startVisited=%b, endVisited=%b", expedition, minute, startVisited, endVisited);
  }
}
