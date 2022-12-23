package org.aoc.tmousaw.day21;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class MonkeyMath extends AdventOfCodeSolver {

  public MonkeyMath() throws IOException {
  }

  public MonkeyMath(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    MonkeyMath monkeyMath = new MonkeyMath();
    monkeyMath.solve();
    monkeyMath.printAnswers();
    System.out.println();
    monkeyMath.printTimings();
  }

  @Override
  public void solve() {
    List<Monkey> monkeyList = new ArrayList<>();
    for (String line : getLinesOfInput()) {
      String[] tokens = line.trim().split(":");
      if (tokens.length < 2) {
        System.err.println("WARNING: Ignoring line did not conform to standard (0). line=" + line);
        continue;
      }
      String name = tokens[0];
      String[] answerParts = tokens[1].trim().split(" ");
      if (answerParts.length < 1) {
        System.err.println("WARNING: Ignoring line did not conform to standard (1). line=" + line);
        continue;
      }

      if (answerParts.length == 1) {
        monkeyList.add(new NumberMonkey(name, Integer.parseInt(answerParts[0])));
      } else if (answerParts.length >= 3) {
        monkeyList.add(new FunctionMonkey(name, answerParts[0], answerParts[2], answerParts[1].charAt(0)));
      } else {
        System.err.println("WARNING: Ignoring line that did not conform to standard (2). line=" + line);
      }
    }

    FunctionMonkey.setOperandMonkey(monkeyList);
    monkeyList.stream().filter(m -> m instanceof FunctionMonkey).forEach(m -> {
      FunctionMonkey functionMonkey = (FunctionMonkey) m;
      functionMonkey.getLeftOperandMonkey().setParent(m);
      functionMonkey.getRightOperandMonkey().setParent(m);
    });
    monkeyList.stream().filter(m -> m instanceof  FunctionMonkey).forEach(m -> ((FunctionMonkey)m).setSupplier());
    monkeyList.forEach(Monkey::setInverseSupplier);
    // Need to set the inverse supplier for 'root' separately.
    monkeyList.stream().filter(m -> m.getName().equals("root")).findFirst().orElseThrow().setInverseSupplier();

    addAnswer("Monkey named 'root' yells", monkeyList.stream().filter(m -> m.getName().equals("root")).findFirst().orElseThrow().getSupplier().get());

    addAnswer("I yell", monkeyList.stream().filter(m -> m.getName().equals("humn")).findFirst().orElseThrow().getInverseSupplier().get());
  }
}
