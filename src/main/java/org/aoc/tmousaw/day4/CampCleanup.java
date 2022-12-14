package org.aoc.tmousaw.day4;

import java.io.IOException;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import com.google.common.collect.Range;

public class CampCleanup extends AdventOfCodeSolver {

  public CampCleanup() throws IOException {
  }

  public static void main(String[] args) throws IOException {
    CampCleanup campCleanup = new CampCleanup();
    campCleanup.solve();
    campCleanup.printAnswers();
    System.out.println();
    campCleanup.printTimings();
  }

  @Override
  public void solve() {
    int completeOverlaps = 0;
    int overlaps = 0;
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        String[] ranges = line.split(",");
        if (ranges.length < 2) {
          System.err.println("ERROR: Input does not contain two items. line=" + line);
          continue;
        }

        if (ranges.length > 2) {
          System.out.println("WARNING: Input contains more than two items. Will ignore items after the second item. line=" + line);
        }

        String assignmentOne = ranges[0];
        String[] sections = assignmentOne.split("-");
        if (sections.length < 2) {
          System.err.println("ERROR: Input does not contain a range of sections. assignmentOne=" + assignmentOne);
          continue;
        }

        if (sections.length > 2) {
          System.out.println("WARNING: Input contains more than two items. Will ignore items after the second item. assignmentOne=" + assignmentOne);
        }
        Range<Integer> rangeOne = Range.closed(Integer.parseInt(sections[0]), Integer.parseInt(sections[1]));

        String assignmentTwo = ranges[1];
        sections = assignmentTwo.split("-");
        if (sections.length < 2) {
          System.err.println("ERROR: Input does not contain a range of sections. assignmentOne=" + assignmentOne);
          continue;
        }

        if (sections.length > 2) {
          System.out.println("WARNING: Input contains more than two items. Will ignore items after the second item. assignmentOne=" + assignmentOne);
        }
        Range<Integer> rangeTwo = Range.closed(Integer.parseInt(sections[0]), Integer.parseInt(sections[1]));

        if (!rangeOne.isConnected(rangeTwo)) {
          continue;
        } else {
          overlaps++;
        }

        Range<Integer> intersection = rangeOne.intersection(rangeTwo);

        if (intersection.equals(rangeOne) || intersection.equals(rangeTwo)) {
          completeOverlaps++;
        }
      }
    }

    addAnswer("Complete Overlaps", completeOverlaps);
    addAnswer("Overlaps", overlaps);
  }
}
