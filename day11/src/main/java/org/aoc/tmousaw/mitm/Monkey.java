package org.aoc.tmousaw.mitm;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Monkey {

  private final List<Long> items;
  private Function<Long, Long> inspectOperation;
  private Function<Long, Boolean> testOperation;
  private int indexIfTrue;
  private int indexIfFalse;
  private int inspectionCount;
  private long worryDivisor;
  private int divisibleBy;
  private long leastCommonMultiple;

  public Monkey() {
    items = new ArrayList<>();
    inspectionCount = 0;
    worryDivisor = 3L;
  }

  public Monkey(Monkey monkey) {
    items = new ArrayList<>();
    items.addAll(monkey.items);
    inspectOperation = monkey.inspectOperation;
    testOperation = monkey.testOperation;
    indexIfTrue = monkey.indexIfTrue;
    indexIfFalse = monkey.indexIfFalse;
    inspectionCount = monkey.inspectionCount;
    worryDivisor = monkey.worryDivisor;
    divisibleBy = monkey.divisibleBy;
    leastCommonMultiple = monkey.leastCommonMultiple;
  }

  public void addItem(int item) {
    items.add((long) item);
  }

  public void addItem(Long item) {
    items.add(item);
  }

  public void setInspectOperation(Function<Long, Long> inspectOperation) {
    this.inspectOperation = inspectOperation;
  }

  public void setTestOperation(Function<Long, Boolean> testOperation) {
    this.testOperation = testOperation;
  }

  public void setIndexIfTrue(int i) {
    indexIfTrue = i;
  }

  public void setIndexIfFalse(int i) {
    indexIfFalse = i;
  }

  public void setWorryDivisor(int i) {
    worryDivisor = i;
  }

  public void setLeastCommonMultiple(long leastCommonMultiple) {
    this.leastCommonMultiple = leastCommonMultiple;
  }

  public int getThrowToIndex() {
    if (items.isEmpty()) {
      throw new NoSuchElementException("Monkey is holding no items.");
    }

    Long item = items.get(0);
    if (testOperation.apply(item)) {
      return indexIfTrue;
    }

    return indexIfFalse;
  }

  public void inspectNextItem() {
    if (items.isEmpty()) {
      throw new NoSuchElementException("Monkey is holding no items.");
    }

    Long item = items.get(0);

    items.set(0, inspectOperation.apply(item) / worryDivisor);

    inspectionCount++;
  }

  public void throwItemTo(Monkey monkey) {
    if (items.isEmpty()) {
      throw new NoSuchElementException("Monkey is holding no items.");
    }
    Long i = items.remove(0);
    monkey.addItem(i % leastCommonMultiple);
  }

  public boolean hasMoreItems() {
    return !items.isEmpty();
  }

  public int getInspectionCount() {
    return inspectionCount;
  }

  public int getDivisibleBy() {
    return divisibleBy;
  }

  public void setDivisibleBy(int i) {
    divisibleBy = i;
  }
}
