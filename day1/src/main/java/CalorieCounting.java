import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCounting {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    int calories = 0;
    String line;

    List<Integer> calorieList = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        calories += Integer.parseInt(line);
      } else {
        calorieList.add(calories);
        calories = 0;
      }
    }

    Collections.sort(calorieList);
    Collections.reverse(calorieList);

    int sumOfTopThreeCalories = calorieList.get(0) + calorieList.get(1) + calorieList.get(2);

    System.out.println(sumOfTopThreeCalories);
  }
}
