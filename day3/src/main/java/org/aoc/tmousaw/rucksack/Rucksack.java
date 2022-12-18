package org.aoc.tmousaw.rucksack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rucksack {
  private Set<Character> compartmentOne;
  private Set<Character> compartmentTwo;

  private Set<Character> contentsSet;

  public Rucksack(final String contents) {
    if (contents.trim().length() > 0) {
      if (contents.length() % 2 != 0) {
        System.out.println("WARNING: contents length is not divisible by 2. Assuming extra item is in second half of rucksack. Length=" + contents.length());
      }

      String firstHalf = contents.substring(0, contents.length() / 2);
      String secondHalf = contents.substring(contents.length() / 2);

      compartmentOne = new HashSet<>();
      compartmentTwo = new HashSet<>();
      contentsSet = new HashSet<>();

      for (int i = 0; i < firstHalf.length(); i++) {
        compartmentOne.add(firstHalf.charAt(i));
        contentsSet.add(firstHalf.charAt(i));
      }

      for (int i = 0; i < secondHalf.length(); i++) {
        compartmentTwo.add(secondHalf.charAt(i));
        contentsSet.add(secondHalf.charAt(i));
      }
    }
  }

  public Set<Character> getContents() {
    return contentsSet;
  }

  public Set<Character> getCompartmentOne() {
    return compartmentOne;
  }

  public Set<Character> getCompartmentTwo() {
    return compartmentTwo;
  }

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    String line;
    List<Rucksack> rucksackList = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      rucksackList.add(new Rucksack(line));
    }

    int prioritySum = 0;
    for (Rucksack rucksack : rucksackList) {
      Set<Character> compartmentCopy = new HashSet<>(rucksack.getCompartmentOne());
      compartmentCopy.retainAll(rucksack.getCompartmentTwo());
      prioritySum += compartmentCopy.stream().mapToInt(Rucksack::getCharacterPriority).sum();
    }

    System.out.println("Sum of priority (Part 1): " + prioritySum);

    prioritySum = 0;
    for (int i = 0; i < rucksackList.size() / 3; i++) {
      if (3*i+2 >= rucksackList.size()) {
        System.out.println("WARNING: Number of rucksacks was not divisible by 3. size=" + rucksackList.size());
        break;
      }

      Rucksack rucksack = rucksackList.get(3*i);
      Set<Character> commonSet = new HashSet<>(rucksack.getContents());
      rucksack = rucksackList.get(3*i+1);
      commonSet.retainAll(rucksack.getContents());
      rucksack = rucksackList.get(3*i+2);
      commonSet.retainAll(rucksack.getContents());
      prioritySum += commonSet.stream().mapToInt(Rucksack::getCharacterPriority).sum();
    }
    System.out.println("Sum of priority (Part 2): " + prioritySum);
  }

  public static int getCharacterPriority(Character c) {
    int asciiValue = (int) c;
    int priority;

    if (asciiValue > 122 || asciiValue < 65) {
      throw new IllegalArgumentException("Character is not alphabetic! c=" + c);
    }

    if (asciiValue >= 97) {
      priority = asciiValue - 96;
    } else { // Must be upper case.
      priority = asciiValue - 38;
    }

    return priority;
  }
}
