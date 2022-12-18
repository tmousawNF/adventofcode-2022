package org.aoc.tmousaw.rps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RockPaperScissors {
  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    int finalScore = 0;
    String line;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        String[] entries = line.split("\\s+");
        if (entries.length < 2) {
          System.err.println("ERROR: Line contained less than two entries: " + line);
          continue;
        } else if (entries.length > 2) {
          System.out.println("WARNING: Line contains more than two entries: " + line);
        }

        Character oppenentGuess = entries[0].charAt(0);
        Character myGuess = entries[1].charAt(0);

        HandShape opponentShape = HandShape.valueOf(oppenentGuess);
        HandShape myShape = HandShape.valueOf(myGuess);

        finalScore += myShape.value;
        finalScore += myShape.versus(opponentShape);
      }
    }

    System.out.println("Final score (Part 1): " + finalScore);

    is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    br = new BufferedReader(reader);

    finalScore = 0;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        String[] entries = line.split("\\s+");
        if (entries.length < 2) {
          System.err.println("ERROR: Line contained less than two entries: " + line);
          continue;
        } else if (entries.length > 2) {
          System.out.println("WARNING: Line contains more than two entries: " + line);
        }

        Character oppenentGuess = entries[0].charAt(0);
        Character resultChar = entries[1].charAt(0);

        HandShape opponentShape = HandShape.valueOf(oppenentGuess);
        Result result = Result.valueOf(resultChar);
        HandShape myShape = opponentShape.getCorrespondingShape(result);

        finalScore += myShape.value;
        finalScore += myShape.versus(opponentShape);
      }
    }

    System.out.println("Final score (Part 2): " + finalScore);
  }
}
