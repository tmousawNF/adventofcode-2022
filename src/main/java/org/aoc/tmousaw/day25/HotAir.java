package org.aoc.tmousaw.day25;

import java.io.IOException;
import java.math.BigInteger;
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
    HotAir hotAir = new HotAir();
    hotAir.solve();
    hotAir.printAnswers();
    System.out.println();
    hotAir.printTimings();
  }

  @Override
  public void solve() {
    BigInteger value = BigInteger.valueOf(0);
    for (String line : getLinesOfInput()) {
      int iter = 0;
      for (int i = line.length() - 1; i >= 0; i--) {
        int digit;
        char c = line.charAt(i);
        if (c == '-' || c == '=') {
          digit = charToIntMap.get(c);
        } else {
          digit = (int) c - '0';
        }

        BigInteger pow = BigInteger.valueOf(10).pow(iter);
        BigInteger operand = BigInteger.valueOf(digit).multiply(pow);
        value = value.add(new BigInteger(operand.toString(), 5));
        iter++;
      }
    }

    String valueString = value.toString(5);
    StringBuilder answer = new StringBuilder();
    boolean carry = false;
    for (int i = valueString.length() - 1; i >= 0; i--) {
      int digit = valueString.charAt(i) - '0';
      if (answer.length() > 0 && (answer.charAt(0) == '-' || answer.charAt(0) == '=' || carry)) {
        digit++;
        carry = false;
      }
      if (digit < 3) {
        answer.insert(0, digit);
      } else {
        if (digit == 3) {
          answer.insert(0, "=");
        } else if (digit == 4){ // digit == 4
          answer.insert(0, "-");
        } else if (digit == 5) {
          answer.insert(0, "0");
          carry = true;
        } else {
          throw new IllegalArgumentException("Digit was " + digit);
        }
      }
    }

    addAnswer("SNAFU number", answer.toString());
  }
}
