package org.aoc.tmousaw.day20;

import java.util.Objects;

public class IndexedLong {
  private final long value;
  private final int index;

  public IndexedLong(long value, int index) {
    this.value = value;
    this.index = index;
  }

  public long getValue() {
    return value;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndexedLong that = (IndexedLong) o;
    return value == that.value && index == that.index;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, index);
  }

  @Override
  public String toString() {
    return String.format("{%d, %d}", value, index);
  }
}
