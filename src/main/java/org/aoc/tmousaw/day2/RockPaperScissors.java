package org.aoc.tmousaw.day2;

import java.io.IOException;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class RockPaperScissors extends AdventOfCodeSolver {

  public RockPaperScissors() throws IOException {
  }

  public static void main(String[] args) throws IOException {
    RockPaperScissors rockPaperScissors = new RockPaperScissors();

    rockPaperScissors.solve();
    rockPaperScissors.printAnswers();
    System.out.println();
    rockPaperScissors.printTimings();
  }

  @Override
  public void solve() {
    int finalScore = 0;
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        String[] entries = line.split("\\s+");
        if (entries.length < 2) {
          System.err.println("ERROR: Line contained less than two entries: " + line);
          continue;
        } else if (entries.length > 2) {
          System.out.println("WARNING: Line contains more than two entries: " + line);
        }

        Character opponentGuess = entries[0].charAt(0);
        Character myGuess = entries[1].charAt(0);

        HandShape opponentShape = HandShape.valueOf(opponentGuess);
        HandShape myShape = HandShape.valueOf(myGuess);

        finalScore += myShape.value;
        finalScore += myShape.versus(opponentShape);
      }
    }
    addAnswer("Final score", finalScore);

    finalScore = 0;
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        String[] entries = line.split("\\s+");
        if (entries.length < 2) {
          System.err.println("ERROR: Line contained less than two entries: " + line);
          continue;
        } else if (entries.length > 2) {
          System.out.println("WARNING: Line contains more than two entries: " + line);
        }

        Character opponentGuess = entries[0].charAt(0);
        Character resultChar = entries[1].charAt(0);

        HandShape opponentShape = HandShape.valueOf(opponentGuess);
        Result result = Result.valueOf(resultChar);
        HandShape myShape = opponentShape.getCorrespondingShape(result);

        finalScore += myShape.value;
        finalScore += myShape.versus(opponentShape);
      }
    }

    addAnswer("Final score", finalScore);
  }
}
