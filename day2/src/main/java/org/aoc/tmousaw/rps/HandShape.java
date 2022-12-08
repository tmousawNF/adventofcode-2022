package org.aoc.tmousaw.rps;

public enum HandShape {
  ROCK(1, 'A', 3, 2),
  PAPER(2, 'B', 1, 3),
  SCISSORS(3, 'C', 2, 1);

  final int value;
  final Character characterEquivalent;
  final int beatsValue;
  final int losesToValue;

  HandShape(int value, Character characterEquivalent, int beatsValue, int losesToValue) {
    this.value = value;
    this.characterEquivalent = characterEquivalent;
    this.beatsValue = beatsValue;
    this.losesToValue = losesToValue;
  }

  public static HandShape valueOf(Character c) {
    for (HandShape handShape : HandShape.values()) {
      if (handShape.characterEquivalent.equals(c)) {
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
