package org.aoc.tmousaw.rps;

import java.util.List;

public enum HandShape {
  ROCK(1, List.of('A', 'X')),
  PAPER(2, List.of('B', 'Y')),
  SCISSORS(3, List.of('C', 'Z'));

  final int value;
  final List<Character> characterEquivalents;

  HandShape(int value, List<Character> characterEquivalents) {
    this.value = value;
    this.characterEquivalents = characterEquivalents;
  }

  public static HandShape valueOf(Character c) {
    for (HandShape handShape : HandShape.values()) {
      if (handShape.characterEquivalents.contains(c)) {
        return handShape;
      }
    }

    throw new IllegalArgumentException("Could not find HandShape equivalent for " + c);
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
