package org.aoc.tmousaw.day6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.aoc.tmousaw.common.AdventOfCodeSolver;

public class TuningTrouble extends AdventOfCodeSolver {

  public TuningTrouble() throws IOException {
  }

  public static void main(String[] args) throws IOException {
    TuningTrouble tuningTrouble = new TuningTrouble();
    tuningTrouble.solve();
    tuningTrouble.printAnswers();
    System.out.println();
    tuningTrouble.printTimings();
  }

  @Override
  public void solve() {

    int markerSize = 0, startOfMessageSize = 0;
    ArrayList<Character> signalList = new ArrayList<>();
    for (String line : getLinesOfInput()) {
      for (int i = 0; i < line.trim().length(); i++) {
        String input = line.trim();
        char c = input.charAt(i);
        signalList.add(c);
        if (signalList.size() >= 4 && markerSize == 0) {
          Set<Character> signalSet = new HashSet<>();
          signalList.listIterator(signalList.size() - 4).forEachRemaining(signalSet::add);

          if (signalSet.size() == 4) {
            markerSize = signalList.size();
          }
        } else if (signalList.size() >= 14) {
          Set<Character> signalSet = new HashSet<>();
          signalList.listIterator(signalList.size() - 14).forEachRemaining(signalSet::add);

          if (signalSet.size() == 14) {
            startOfMessageSize = signalList.size();
            break;
          }
        }
      }
    }

    addAnswer("Marker size", markerSize);
    addAnswer("Start of message size", startOfMessageSize);
  }
}
