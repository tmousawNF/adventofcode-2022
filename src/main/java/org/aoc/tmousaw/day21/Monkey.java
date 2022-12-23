package org.aoc.tmousaw.day21;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Supplier;

public class Monkey {

  private final String name;

  private Monkey parent;
  private Supplier<Long> supplier;
  private Supplier<Long> inverseSupplier;

  public Monkey(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Supplier<Long> getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier<Long> supplier) {
    this.supplier = supplier;
  }

  public Supplier<Long> getInverseSupplier() {
    return inverseSupplier;
  }

  public void setInverseSupplier(Supplier<Long> inverseSupplier) {
    this.inverseSupplier = inverseSupplier;
  }

  public Monkey getParent() {
    return parent;
  }

  public void setParent(Monkey parent) {
    this.parent = parent;
  }

  public boolean isLeftMonkey() {
    if (getName().equals("root")) {
      return true;
    }
    return ((FunctionMonkey) getParent()).getLeftOperandMonkey().equals(this);
  }

  public void setInverseSupplier() {
    if (parent != null) {
      if (parent.getName().equals("root")) {
        if (parent.humanInLeftSubtree()) {
          setInverseSupplier(((FunctionMonkey) parent).getRightOperandMonkey().getSupplier());
        } else {
          setInverseSupplier(((FunctionMonkey) parent).getLeftOperandMonkey().getSupplier());
        }
      } else {
        char operator = ((FunctionMonkey) parent).getOperator();
        Supplier<Long> siblingSupplier = ((FunctionMonkey) getParent()).getOtherChild(this).getSupplier();
        if (operator == '+') {
          setInverseSupplier(() -> getParent().getInverseSupplier().get() - siblingSupplier.get());
        } else if (operator == '-') {
          if (isLeftMonkey()) {
            setInverseSupplier(() -> siblingSupplier.get() + getParent().getInverseSupplier().get());
          } else {
            setInverseSupplier(() -> siblingSupplier.get() - getParent().getInverseSupplier().get());
          }
        } else if (operator == '*') {
          setInverseSupplier(() -> getParent().getInverseSupplier().get() / siblingSupplier.get());
        } else if (operator == '/') {
          if (isLeftMonkey()) {
            setInverseSupplier(() -> siblingSupplier.get() * getParent().getInverseSupplier().get());
          } else {
            setInverseSupplier(() -> siblingSupplier.get() / getParent().getInverseSupplier().get());
          }
        }
      }
    }
  }

  private boolean humanInLeftSubtree() {
    Monkey monkey = ((FunctionMonkey) this).getLeftOperandMonkey();

    Queue<Monkey> queue = new ArrayDeque<>();
    queue.add(monkey);
    while (!queue.isEmpty()) {
      monkey = queue.remove();
      if (monkey.getName().equals("humn")) {
        return true;
      }

      if (monkey instanceof FunctionMonkey) {
        queue.add(((FunctionMonkey) monkey).getLeftOperandMonkey());
        queue.add(((FunctionMonkey) monkey).getRightOperandMonkey());
      }
    }

    return false;
  }
}
