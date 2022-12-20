package org.aoc.tmousaw.day19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class Mining extends AdventOfCodeSolver {

  private static final Pattern p = Pattern.compile(
      "Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. " + "Each obsidian robot costs (\\d+) ore and (\\d+) clay. "
          + "Each geode robot costs (\\d+) ore and (\\d+) obsidian.");

  public Mining() throws IOException {
    super();
  }

  public static void main(String[] args) throws IOException {
    Mining mining = new Mining();
    mining.solve();
    mining.printAnswers();
    System.out.println();
    mining.printTimings();
  }

  @Override
  public void solve() {
    List<Blueprint> blueprints = new ArrayList<>();
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        Matcher m = p.matcher(line.trim());
        m.find();
        blueprints.add(new Blueprint(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)),
            Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6))));
      }
    }
    blueprints.forEach(System.out::println);

    blueprints.forEach(b -> {
      Inventory inventory = new Inventory();
      Plant plant = new Plant(b);
      for (int minutes = 0; minutes < 24; minutes++) {
        plant.process();
      }
    });
  }
}
