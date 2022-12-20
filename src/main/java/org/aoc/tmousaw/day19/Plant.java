package org.aoc.tmousaw.day19;

import static org.aoc.tmousaw.day19.RobotType.CLAY;
import static org.aoc.tmousaw.day19.RobotType.GEODE;
import static org.aoc.tmousaw.day19.RobotType.OBSIDIAN;
import static org.aoc.tmousaw.day19.RobotType.ORE;

public class Plant {
  private int numOreRobots;
  private int numClayRobots;
  private int numObsidianRobots;
  private int numGeodeRobots;

  private boolean[] buildRobots;
  private Inventory inventory;
  private Blueprint blueprint;

  public Plant(Blueprint blueprint) {
    numOreRobots = 1;
    numClayRobots = 0;
    numObsidianRobots = 0;
    numGeodeRobots = 0;
    buildRobots = new boolean[4];
    this.blueprint = blueprint;
    inventory = new Inventory();
  }

  public void addOreRobot() {
    numOreRobots++;
  }

  public void addClayRobot() {
    numClayRobots++;
  }

  public void addObsidianRobot() {
    numObsidianRobots++;
  }

  public void addGeodeRobot() {
    numGeodeRobots++;
  }

  public int getNumOreRobots() {
    return numOreRobots;
  }

  public int getNumClayRobots() {
    return numClayRobots;
  }

  public int getNumObsidianRobots() {
    return numObsidianRobots;
  }

  public int getNumGeodeRobots() {
    return numGeodeRobots;
  }

  public void process() {
    determineRobotsToBuild();
    inventory.addOre(numOreRobots);
    inventory.addClay(numClayRobots);
    inventory.addObsidian(numObsidianRobots);
  }

  private void determineRobotsToBuild() {
    if (inventory.getObsidian() >= blueprint.getGeodeRobotObsidianCost() && inventory.getOre() >= blueprint.getGeodeRobotOreCost()) {
      buildRobots[GEODE] = true;
    } else if (inventory.getClay() >= blueprint.getObsidianRobotClayCost() && inventory.getOre() >= blueprint.getObsidianRobotOreCost()) {
      buildRobots[OBSIDIAN] = true;
    } else if (inventory.getOre() >= blueprint.getClayRobotCost()) {
      buildRobots[CLAY] = true;
    } else if (inventory.getOre() >= blueprint.getOreRobotCost()) {
      buildRobots[ORE] = true;
    }
  }
}
