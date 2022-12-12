package org.aoc.tmousaw.mitm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MonkeyInTheMiddle {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    List<Monkey> monkeys = new ArrayList<>();
    Monkey currentMonkey = null;
    String line;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        String input = line.trim();

        String[] tokens = input.split(":");
        if (input.startsWith("Monkey")) {
          currentMonkey = new Monkey();
          monkeys.add(currentMonkey);
        } else if (input.startsWith("Starting items:")) {
          assert currentMonkey != null;
          String[] items = tokens[1].trim().split(",");
          for (String item : items) {
            currentMonkey.addItem(Integer.parseInt(item.trim()));
          }
        } else if (input.startsWith("Operation")) {
          assert currentMonkey != null;
          String formula = tokens[1].trim();
          String operation = formula.split("=")[1];
          String[] operationParts = operation.trim().split(" ");
          String operator = operationParts[1];

          if ("+".equals(operator)) {
            currentMonkey.setInspectOperation(WorryLevel.addOperand(Integer.parseInt(operationParts[2].trim())));
          } else if ("*".equals(operator)) {
            if ("old".equals(operationParts[2].trim())) {
              currentMonkey.setInspectOperation(WorryLevel.square());
            } else {
              currentMonkey.setInspectOperation(WorryLevel.multiplyOperand(Integer.parseInt(operationParts[2].trim())));
            }
          }
        } else if (input.startsWith("Test")) {
          assert currentMonkey != null;
          String[] testParts = tokens[1].trim().split(" ");
          int divisibleBy = Integer.parseInt(testParts[2].trim());
          currentMonkey.setDivisibleBy(divisibleBy);
          currentMonkey.setTestOperation(x -> (x % (long) divisibleBy) == 0);
        } else if (input.startsWith("If true")) {
          assert currentMonkey != null;
          String[] throwToParts = tokens[1].trim().split(" ");
          currentMonkey.setIndexIfTrue(Integer.parseInt(throwToParts[3].trim()));
        } else if (input.startsWith("If false")) {
          assert currentMonkey != null;
          String[] throwToParts = tokens[1].trim().split(" ");
          currentMonkey.setIndexIfFalse(Integer.parseInt(throwToParts[3].trim()));
        }
      }
    }

    long leastCommonMultiple = 1;
    for (Monkey monkey : monkeys) {
      leastCommonMultiple *= monkey.getDivisibleBy();
    }

    for (Monkey monkey : monkeys) {
      monkey.setLeastCommonMultiple(leastCommonMultiple);
    }

    // Copy the list for part two.
    List<Monkey> monkeysForPartTwo = new ArrayList<>();
    for (Monkey monkey : monkeys) {
      Monkey monkeyCopy = new Monkey(monkey);
      monkeyCopy.setWorryDivisor(1);
      monkeysForPartTwo.add(monkeyCopy);
    }

    // Part 1.
    for (int i = 0; i < 20; i++) {
      performRound(monkeys);
    }

    long largestValue = 0L, secondLargestValue = 0L;
    for (Monkey monkey : monkeys) {
      if (monkey.getInspectionCount() > largestValue) {
        secondLargestValue = largestValue;
        largestValue = monkey.getInspectionCount();
      } else if (monkey.getInspectionCount() > secondLargestValue) {
        secondLargestValue = monkey.getInspectionCount();
      }
    }

    System.out.println("Level of Monkey Business after 20 rounds (Part 1): " + largestValue * secondLargestValue);

    // Part 2.
    for (int i = 0; i < 10000; i++) {
      performRound(monkeysForPartTwo);
    }

    largestValue = secondLargestValue = 0L;
    for (Monkey monkey : monkeysForPartTwo) {
      if (monkey.getInspectionCount() > largestValue) {
        secondLargestValue = largestValue;
        largestValue = monkey.getInspectionCount();
      } else if (monkey.getInspectionCount() > secondLargestValue) {
        secondLargestValue = monkey.getInspectionCount();
      }
    }

    System.out.println("Level of Monkey Business after 10000 rounds (Part 2): " + largestValue * secondLargestValue);
  }

  private static void performRound(List<Monkey> monkeys) {
    for (Monkey monkey : monkeys) {
      while (monkey.hasMoreItems()) {
        monkey.inspectNextItem();
        int monkeyIndex = monkey.getThrowToIndex();
        monkey.throwItemTo(monkeys.get(monkeyIndex));
      }
    }
  }
}
