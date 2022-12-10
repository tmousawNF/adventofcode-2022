package org.aoc.tmousaw.fs.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeTest {
  Tree<Integer> rootTree;

  @BeforeEach
  public void setUp() {
    rootTree = new Tree<>(0);
    rootTree.addChild(1);
    rootTree.addChild(2);
    rootTree.addChild(3);
    rootTree.getChildren().get(0).addChild(4);
    rootTree.getChildren().get(1).addChild(5);
    rootTree.getChildren().get(1).addChild(6);
    rootTree.getChildren().get(1).addChild(7);
    rootTree.getChildren().get(1).getChildren().get(1).addChild(8);
    rootTree.getChildren().get(1).getChildren().get(1).addChild(9);
    rootTree.getChildren().get(1).getChildren().get(1).addChild(10);
  }

  @Test
  public void testCreate() {
    assertEquals(0, rootTree.getData());
    assertNull(rootTree.getParent());
    assertEquals(3, rootTree.getChildren().size());
  }

  @Test
  public void testIterator() {
    int iterations = 0;
    for (Integer i : rootTree) {
      iterations++;
    }

    assertEquals(11, iterations);
  }
}
