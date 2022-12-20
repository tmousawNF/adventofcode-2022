package org.aoc.tmousaw.day19;

public class Inventory {
  private int ore;
  private int clay;
  private int obsidian;
  private int geode;

  public Inventory() {
    ore = 0;
    clay = 0;
    obsidian = 0;
    geode = 0;
  }

  public int getOre() {
    return ore;
  }

  public int getClay() {
    return clay;
  }

  public int getObsidian() {
    return  obsidian;
  }

  public int getGeode() {
    return geode;
  }

  public void addOre(int numOre) {
    ore += numOre;
  }

  public void addClay(int amt) {
    clay += amt;
  }

  public void addObsidian(int amt) {
    obsidian += amt;
  }

  public void addGeode(int amt) {
    geode += amt;
  }

  public void spendOre(int cost) {
    ore -= cost;
  }

  public void spendClay(int cost) {
    clay -= cost;
  }

  public void spendObsidian(int cost) {
    obsidian -= cost;
  }
}
