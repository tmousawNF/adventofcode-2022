package org.aoc.tmousaw.day25;

import java.io.IOException;
import java.util.Map;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class HotAir extends AdventOfCodeSolver {

  private static final Map<Character, Integer> charToIntMap = Map.of('-', -1, '=', -2);

  public HotAir() throws IOException {
    this("input.txt");
  }

  public HotAir(String resourceFileName) throws IOException {
    super(resourceFileName);
  }

  public static void main(String[] args) throws IOException {
    HotAir hotAir = new HotAir("sample.txt");
    hotAir.solve();
    hotAir.printAnswers();
    System.out.println();
    hotAir.printTimings();
  }

  @Override
  public void solve() {
    long value = 0;
    for (String line : getLinesOfInput()) {
      int iter = 0;
      for (int i = line.length() - 1; i >= 0; i--) {
        int digit = 0;
        char c = line.charAt(i);
        if (c == '-' || c == '=') {
          digit = charToIntMap.get(c);
        } else {
          digit = (int) c - '0';
        }

        long pow = (long) Math.pow(10, iter);
        value += Long.parseLong(String.valueOf(digit * pow), 5);
        iter++;
      }
    }

    String valueString = Long.toString(Long.parseLong("" + value), 5);
    System.out.println(valueString);
    String answer = "";
    for (int i = valueString.length() - 1; i >= 0; i--) {
      int digit = valueString.charAt(i) - '0';
      if (answer.length() > 0 && (answer.charAt(0) == '-' || answer.charAt(0) == '=')) {
        digit++;
      }
      if (digit < 3) {
        answer = digit + answer;
      } else {
        if (digit == 3) {
          answer = "=" + answer;
        } else if (digit == 4){ // digit == 4
          answer = "-" + answer;
        } else {
          throw new IllegalArgumentException("Digit was " + digit);
        }
      }
    }

    System.out.println(answer);
    addAnswer("SNAFU number", answer);
  }
}
