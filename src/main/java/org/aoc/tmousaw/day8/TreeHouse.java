package org.aoc.tmousaw.day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class TreeHouse extends AdventOfCodeSolver {
  private final List<List<Integer>> listOfRows;
  private final List<List<Integer>> listOfColumns;

  public TreeHouse() throws IOException {
    listOfRows = new ArrayList<>();
    listOfColumns = new ArrayList<>();
  }

  public static void main(String[] args) throws IOException {
    TreeHouse treeHouse = new TreeHouse();
    treeHouse.solve();
    treeHouse.printAnswers();
    System.out.println();
    treeHouse.printTimings();
  }


  @Override
  public void solve() {

    boolean listOfColumnsInitialized = false;
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        String input = line.trim();
        if (!listOfColumnsInitialized) {
          for (int i = 0; i < input.length(); i++) {
            listOfColumns.add(new ArrayList<>());
          }
          listOfColumnsInitialized = true;
        }

        List<Integer> rowHeights = new ArrayList<>();
        listOfRows.add(rowHeights);
        for (int i = 0; i < input.length(); i++) {
          int height = Integer.parseInt(input.substring(i, i + 1));
          rowHeights.add(height);
          listOfColumns.get(i).add(height);
        }
      }
    }

    int numberOfTreesVisible = 0;
    int numberOfRows = listOfColumns.size();
    int numberOfColumns = listOfRows.size();
    int maxScenicScore = 0;
    for (int row = 0; row < numberOfRows; row++) {
      for (int column = 0; column < numberOfColumns; column++) {
        if (isVisible(row, column)) {
          numberOfTreesVisible++;
        }
        int scenicScore = getScenicScore(row, column);
        if (scenicScore > maxScenicScore) {
          maxScenicScore = scenicScore;
        }
      }
    }

    addAnswer("Number of visible trees", numberOfTreesVisible);
    addAnswer("Max scenic score", maxScenicScore);
  }

  private int getScenicScore(int row, int column) {
    List<Integer> rowHeightList = listOfRows.get(row);
    List<Integer> columnHeightList = listOfColumns.get(column);

    // The column index of the row and the row index of the column should be the same value. Otherwise, I did something wrong.
    assert rowHeightList.get(column).equals(columnHeightList.get(row));

    int rowScenicScoreOfTree = partialScenicScoreOfTree(rowHeightList, column);
    int columnScenicScoreOfTree = partialScenicScoreOfTree(columnHeightList, row);

    return rowScenicScoreOfTree * columnScenicScoreOfTree;
  }

  private boolean isVisible(int row, int column) {
    List<Integer> rowHeightList = listOfRows.get(row);
    List<Integer> columnHeightList = listOfColumns.get(column);

    // The column index of the row and the row index of the column should be the same value. Otherwise, I did something wrong.
    assert rowHeightList.get(column).equals(columnHeightList.get(row));
    return !hasTreeAtLeastSameHeightInBothDirections(rowHeightList, column) || !hasTreeAtLeastSameHeightInBothDirections(columnHeightList, row);
  }

  private int partialScenicScoreOfTree(List<Integer> list, int startIndex) {
    final Integer height = list.get(startIndex);

    ListIterator<Integer> forwardListIterator = list.listIterator(startIndex);
    ListIterator<Integer> backwardIterator = list.listIterator(startIndex);

    // First, traverse the forward iterator to the initial tree.
    if (forwardListIterator.hasNext()) {
      Integer beginningTreeHeight = forwardListIterator.next();
      assert beginningTreeHeight.equals(height); // If these values aren't the same I did something wrong.
    }

    // The backward iterator will start at the tree before this tree so no need to iterate.

    int treesForward = 0;
    while (forwardListIterator.hasNext()) {
      treesForward++;
      if (forwardListIterator.next() >= height) {
        break;
      }
    }

    int treesBackwards = 0;
    while (backwardIterator.hasPrevious()) {
      treesBackwards++;
      if (backwardIterator.previous() >= height) {
        break;
      }
    }

    return treesForward * treesBackwards;
  }

  private boolean hasTreeAtLeastSameHeightInBothDirections(List<Integer> list, int startIndex) {
    final Integer height = list.get(startIndex);

    boolean foundTreeBackwards = false;
    boolean foundTreeForward = false;
    ListIterator<Integer> forwardListIterator = list.listIterator(startIndex);
    ListIterator<Integer> backwardIterator = list.listIterator(startIndex);

    // First, traverse the forward iterator to the initial tree.
    if (forwardListIterator.hasNext()) {
      Integer beginningTreeHeight = forwardListIterator.next();
      assert beginningTreeHeight.equals(height); // If these values aren't the same I did something wrong.
    }

    // The backward iterator will start at the tree before this tree so no need to iterate.

    while (forwardListIterator.hasNext()) {
      if (forwardListIterator.next() >= height) {
        foundTreeForward = true;
        break;
      }
    }

    while (backwardIterator.hasPrevious()) {
      if (backwardIterator.previous() >= height) {
        foundTreeBackwards = true;
        break;
      }
    }

    return foundTreeForward && foundTreeBackwards;
  }
}
