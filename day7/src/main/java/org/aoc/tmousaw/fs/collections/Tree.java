package org.aoc.tmousaw.fs.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<T> implements Iterable<T> {

  private final T data;
  private final Tree<T> parent;
  private final List<Tree<T>> children;

  public Tree(T data) {
    this(data, null);
  }

  public Tree(T data, Tree<T> parent) {
    this.data = data;
    this.parent = parent;
    children = new ArrayList<>();
  }

  public Tree<T> addChild(T data) {
    Tree<T> child = new Tree<>(data, this);
    children.add(child);
    return child;
  }

  @Override
  public Iterator<T> iterator() {
    return new TreeIterator<>(this);
  }

  public T getData() {
    return data;
  }

  public Tree<T> getParent() {
    return parent;
  }

  public List<Tree<T>> getChildren() {
    return children;
  }
}
