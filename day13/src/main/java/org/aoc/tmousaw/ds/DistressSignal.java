package org.aoc.tmousaw.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DistressSignal {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    // Would use Guava Pair, but use List for simplicity.
    List<List<Packet>> packetPairList = new ArrayList<>();
    List<Packet> packetPair = new ArrayList<>();
    String line;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        Packet packet = new Packet(line.trim());
        packetPair.add(packet);
      } else {
        addPacketPair(packetPairList, packetPair);
        packetPair = new ArrayList<>();
        // Ignore if packetPair is empty... could be two newlines at end of file.
      }
    }

    // Need to add the last packet pair after the while loop.
    addPacketPair(packetPairList, packetPair);

    // printListOfPairs(packetPairList);
    int sumOfCorrectlyPairedIndices = 0;
    for (int i = 1; i <= packetPairList.size(); i++) {
      List<Packet> pair = packetPairList.get(i - 1);
      Packet packet1 = pair.get(0);
      Packet packet2 = pair.get(1);

      if (compare(packet1.getData(), packet2.getData()) == -1) {
        // System.out.println("correct index: " + i);
        // printPacketPair(pair);
        sumOfCorrectlyPairedIndices += i;
      }
    }

    System.out.println("Sum of correctly ordered indices (Part 1): " + sumOfCorrectlyPairedIndices);

    List<Packet> packetList = extractAllPackets(packetPairList);
    packetList.addAll(List.of(new Packet("[[2]]"), new Packet("[[6]]")));
    
    sort(packetList);
    // printPacketList(packetList);

    int decoderKey = 1;
    Packet dividerStart = new Packet("[[2]]");
    Packet dividerEnd = new Packet("[[6]]");
    for (int i = 1; i <= packetList.size(); i++) {
      Packet p = packetList.get(i - 1);
      if (compare(p.getData(), dividerStart.getData()) == 0 || (compare(p.getData(), dividerEnd.getData())) == 0) {
        decoderKey *= i;
      }
    }
    System.out.println("Decoder key (Part 2): " + decoderKey);
  }

  private static void sort(List<Packet> packetList) {
    // Simple bubble sort algorithm.
    int n = packetList.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (compare(packetList.get(j).getData(), packetList.get(j + 1).getData()) == 1) {
          Packet p = packetList.get(j);
          packetList.set(j, packetList.get(j + 1));
          packetList.set(j + 1, p);
        }
      }
    }
  }

  private static List<Packet> extractAllPackets(List<List<Packet>> packetPairList) {
    List<Packet> newList = new ArrayList<>();
    for (List<Packet> packetList : packetPairList) {
      newList.addAll(packetList);
    }

    return newList;
  }

  private static void addPacketPair(List<List<Packet>> packetPairList, List<Packet> packetPair) {
    if (!packetPair.isEmpty()) {
      if (packetPair.size() == 1 || packetPair.size() > 2) {
        throw new IllegalArgumentException("packetPair should only have two entries. size=" + packetPair.size());
      } else {
        packetPairList.add(packetPair);
      }
    }
  }

  private static int compare(List<Object> packet1Data, List<Object> packet2Data) {
    int inOrder;
    for (int i = 0; (i < packet1Data.size()); i++) {
      if (i >= packet2Data.size()) {
        // Right side ran out of items. It's out of order.
        return 1;
      }
      Object packet1Item = packet1Data.get(i);
      Object packet2Item = packet2Data.get(i);

      if (packet1Item instanceof Integer && packet2Item instanceof List) {
        packet1Item = List.of(packet1Item);
      } else if (packet1Item instanceof List && packet2Item instanceof Integer) {
        packet2Item = List.of(packet2Item);
      }

      if (packet1Item instanceof List) {
        inOrder = compare((List<Object>) packet1Item, (List<Object>) packet2Item);
        if (inOrder != 0) {
          return inOrder;
        }
      } else {
        if ((int) packet1Item < (int) packet2Item) {
          return -1;
        }

        if ((int) packet1Item > (int) packet2Item) {
          return 1;
        }

        // Else they are equal and we should continue.
      }
    }

    // We got through both lists and the right was the same size or bigger than the left.
    if (packet1Data.size() == packet2Data.size()) {
      return 0;
    }

    return -1;
  }

  private static void printListOfPairs(List<List<Packet>> packetPairList) {
    for (List<Packet> packetPair : packetPairList) {
      printPacketList(packetPair);
    }
  }

  private static void printPacketList(List<Packet> packetList) {
    for (Packet packet : packetList) {
      printPacketData(packet);
    }
    System.out.println();
  }

  private static void printPacketData(Packet packet) {
    printData(packet.getData());
    System.out.println();
  }
  private static void printData(Object data) {
    if (data instanceof List) {
      System.out.print("[");
      for (int i = 0; i < ((List<Object>) data).size(); i++) {
        List<Object> l = (List<Object>) data;
        Object o = l.get(i);
        printData(o);
        if (i < l.size() - 1) {
          System.out.print(",");
        }
      }
      System.out.print("]");
    } else if (data instanceof Integer) {
      System.out.print(data);
    }
  }
}
