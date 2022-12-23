package org.aoc.tmousaw.day21;

public class NumberMonkey extends Monkey {
  public NumberMonkey(String name, long number) {
    super(name);
    setSupplier(() -> number);
  }
}
