package org.aoc.tmousaw.day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class CalorieCounting extends AdventOfCodeSolver {

  public CalorieCounting() throws IOException {
  }

  public static void main(String[] args) throws IOException {
    CalorieCounting calorieCounting = new CalorieCounting();

    calorieCounting.solve();
    calorieCounting.printAnswers();
    System.out.println();
    calorieCounting.printTimings();
  }

  @Override
  public void solve() {
    int calories = 0;
    List<Integer> calorieList = new ArrayList<>();
    for (String line : getLinesOfInput()) {
      String input = line.trim();
      if (input.length() > 0) {
        calories += Integer.parseInt(line);
      } else {
        calorieList.add(calories);
        calories = 0;
      }
    }

    Collections.sort(calorieList);
    Collections.reverse(calorieList);

    addAnswer("Most calories carried", calorieList.get(0));
    int sumOfTopThreeCalories = calorieList.get(0) + calorieList.get(1) + calorieList.get(2);
    addAnswer("Sum of top three calories carried", sumOfTopThreeCalories);
  }
}
