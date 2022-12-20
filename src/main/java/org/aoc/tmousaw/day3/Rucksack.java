package org.aoc.tmousaw.day3;

import java.util.HashSet;
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
