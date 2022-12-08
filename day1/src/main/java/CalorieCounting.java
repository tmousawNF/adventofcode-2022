import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CalorieCounting {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    int calories = 0;
    int maxCalories = 0;
    String line;

    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        calories += Integer.parseInt(line);
      } else {
        // Empty line. Check if this number of calories is most.
        if (calories > maxCalories) {
          maxCalories = calories;
        }
        calories = 0;
      }
    }

    System.out.println(maxCalories);
  }
}
