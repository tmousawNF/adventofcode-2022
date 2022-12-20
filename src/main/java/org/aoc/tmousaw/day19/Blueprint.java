package org.aoc.tmousaw.day19;

public class Blueprint {

  private int oreRobotCost;
  private int clayRobotCost;
  private int obsidianRobotOreCost;
  private int obsidianRobotClayCost;
  private int geodeRobotOreCost;
  private int geodeRobotObsidianCost;

  public Blueprint(int oreRobotCost, int clayRobotCost, int obsidianRobotOreCost, int obsidianRobotClayCost, int geodeRobotOreCost,
      int geodeRobotObsidianCost) {
    this.oreRobotCost = oreRobotCost;
    this.clayRobotCost = clayRobotCost;
    this.obsidianRobotOreCost = obsidianRobotOreCost;
    this.obsidianRobotClayCost = obsidianRobotClayCost;
    this.geodeRobotOreCost = geodeRobotOreCost;
    this.geodeRobotObsidianCost = geodeRobotObsidianCost;
  }

  @Override
  public String toString() {
    return (String.format("Blueprint ({%d}, {%d}, {%d, %d}, {%d, %d})",
        oreRobotCost, clayRobotCost, obsidianRobotOreCost, obsidianRobotClayCost, geodeRobotOreCost, geodeRobotObsidianCost));
  }

  public int getOreRobotCost() {
    return oreRobotCost;
  }

  public int getClayRobotCost() {
    return clayRobotCost;
  }

  public int getObsidianRobotOreCost() {
    return obsidianRobotOreCost;
  }

  public int getObsidianRobotClayCost() {
    return obsidianRobotClayCost;
  }

  public int getGeodeRobotOreCost() {
    return geodeRobotOreCost;
  }

  public int getGeodeRobotObsidianCost() {
    return geodeRobotObsidianCost;
  }
}
