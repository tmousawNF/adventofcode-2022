package org.aoc.tmousaw.cleanup;

import com.google.common.collect.Range;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CampCleanup {
  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    int completeOverlaps = 0;
    int overlaps = 0;
    String line;
    while ((line = br.readLine()) != null) {
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

    System.out.println("Complete Overlaps (Part 1): " + completeOverlaps);
    System.out.println("Overlaps (Part2):" + overlaps);
  }
}
