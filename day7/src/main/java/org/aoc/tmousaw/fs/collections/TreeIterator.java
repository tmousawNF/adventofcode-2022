package org.aoc.tmousaw.fs.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TreeIterator<T> implements Iterator<T> {
  private final Stack<Tree<T>> nodes;

  public TreeIterator(Tree<T> tree) {
    nodes = new Stack<>();
    visit(tree);
  }

  @Override
  public boolean hasNext() {
    return !nodes.isEmpty();
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException("no more nodes");
    }

    return nodes.pop().getData();
  }

  public void visit(Tree<T> tree) {
    for (Tree<T> child : tree.getChildren()) {
      visit(child);
    }

    nodes.push(tree);
  }
}
