package org.aoc.tmousaw.day2;

import java.util.Set;

public enum HandShape {
  ROCK(1, Set.of('A', 'X'), 3, 2),
  PAPER(2, Set.of('B', 'Y'), 1, 3),
  SCISSORS(3, Set.of('C', 'Z'), 2, 1);

  final int value;
  final Set<Character> characterEquivalents;
  final int beatsValue;
  final int losesToValue;

  HandShape(int value, Set<Character> characterEquivalents, int beatsValue, int losesToValue) {
    this.value = value;
    this.characterEquivalents = characterEquivalents;
    this.beatsValue = beatsValue;
    this.losesToValue = losesToValue;
  }

  public static HandShape valueOf(Character c) {
    for (HandShape handShape : HandShape.values()) {
      if (handShape.characterEquivalents.contains(c)) {
        return handShape;
      }
    }

    throw new IllegalArgumentException("Could not find HandShape equivalent for " + c);
  }

  public static HandShape valueOf(int value) {
    for (HandShape handShape : HandShape.values()) {
      if (handShape.value == value) {
        return handShape;
      }
    }

    throw new IllegalArgumentException("Could not find HandShape equivalent for " + value);
  }

  public HandShape getCorrespondingShape(Result result) {
    if (Result.DRAW.equals(result)) {
      return this;
    } else if (Result.LOSE.equals(result)) {
      return valueOf(beatsValue);
    } else if (Result.WIN.equals(result)) {
      return valueOf(losesToValue);
    }

    throw new IllegalArgumentException("Unknown Result " + result);
  }

  public int versus(HandShape shape) {
    if ((ROCK.equals(this) && SCISSORS.equals(shape))
        || (PAPER.equals(this) && ROCK.equals(shape))
        || (SCISSORS.equals(this) && PAPER.equals(shape))) {
      return 6; // WIN
    } else if ((ROCK.equals(this) && PAPER.equals(shape))
        || (PAPER.equals(this) && SCISSORS.equals(shape))
        || (SCISSORS.equals(this) && ROCK.equals(shape))) {
      return 0; // LOSS
    }

    return 3; // DRAW
  }
}
