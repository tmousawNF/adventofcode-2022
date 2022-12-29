package org.aoc.tmousaw.day11;

import java.util.function.Function;

public class WorryLevel {

  public static Function<Long, Long> addOperand(int operand) {
    return (x -> x + operand);
  }

  public static Function<Long, Long> multiplyOperand(int operand) {
    return (x -> x * operand);
  }

  public static Function<Long, Long> square() {
    return (x -> x * x);
  }
}
