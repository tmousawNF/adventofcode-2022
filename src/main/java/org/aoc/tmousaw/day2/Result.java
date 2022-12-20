package org.aoc.tmousaw.day2;

public enum Result {
  LOSE('X'),
  DRAW('Y'),
  WIN('Z');

  final Character characterEquivalent;
  Result(Character characterEquivalent) {
    this.characterEquivalent = characterEquivalent;
  }

  public static Result valueOf(Character character) {
    for (Result result : Result.values()) {
      if (result.characterEquivalent.equals(character)) {
        return result;
      }
    }

    throw new IllegalArgumentException("No Result equivalent found for " + character);
  }
}
