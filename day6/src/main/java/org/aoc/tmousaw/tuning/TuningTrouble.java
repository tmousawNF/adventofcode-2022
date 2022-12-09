package org.aoc.tmousaw.tuning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TuningTrouble {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    Reader br = new BufferedReader(reader);

    int r, markerSize = 0, startOfMessageSize = 0;
    ArrayList<Character> signalList = new ArrayList<>();
    while ((r = br.read()) != -1) {
      signalList.add((char) r);
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

    System.out.println("Marker size (Part 1): " + markerSize);
    System.out.println("Start of message size (Part 2): " + startOfMessageSize);
  }
}
