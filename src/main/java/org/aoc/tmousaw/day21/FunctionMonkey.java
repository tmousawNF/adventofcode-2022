package org.aoc.tmousaw.day21;

import java.util.List;

public class FunctionMonkey extends Monkey {

  private final String leftOperand;
  private final String rightOperand;
  private final char operator;
  private Monkey leftOperandMonkey;
  private Monkey rightOperandMonkey;

  public FunctionMonkey(String name, String leftOperand, String rightOperand, char operator) {
    super(name);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.operator = operator;
  }

  public static void setOperandMonkey(List<Monkey> monkeyList) {
    for (Monkey monkey : monkeyList) {
      if (monkey instanceof FunctionMonkey) {
        FunctionMonkey functionMonkey = (FunctionMonkey) monkey;
        functionMonkey.leftOperandMonkey = monkeyList.stream().filter(m -> m.getName().equals(functionMonkey.leftOperand)).findFirst().orElseThrow();
        functionMonkey.rightOperandMonkey = monkeyList.stream().filter(m -> m.getName().equals(functionMonkey.rightOperand)).findFirst().orElseThrow();
      }
    }
  }

  public Monkey getLeftOperandMonkey() {
    return leftOperandMonkey;
  }

  public Monkey getRightOperandMonkey() {
    return rightOperandMonkey;
  }

  public Monkey getOtherChild(Monkey monkey) {
    if (leftOperandMonkey.equals(monkey)) {
      return rightOperandMonkey;
    }

    return leftOperandMonkey;
  }

  public char getOperator() {
    return operator;
  }

  public void setSupplier() {
    if (operator == '+') {
      setSupplier(() -> leftOperandMonkey.getSupplier().get() + rightOperandMonkey.getSupplier().get());
    } else if (operator == '-') {
      setSupplier(() -> leftOperandMonkey.getSupplier().get() - rightOperandMonkey.getSupplier().get());
    } else if (operator == '*') {
      setSupplier(() -> leftOperandMonkey.getSupplier().get() * rightOperandMonkey.getSupplier().get());
    } else if (operator == '/') {
      setSupplier(() -> leftOperandMonkey.getSupplier().get() / rightOperandMonkey.getSupplier().get());
    } else {
      throw new IllegalArgumentException("Unknown operator " + operator);
    }
  }
}
