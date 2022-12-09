package org.aoc.tmousaw.stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Stack;

public class SupplyStacks {

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    ArrayList<Stack<Character>> supplyStackList = new ArrayList<>();
    ArrayList<Stack<Character>> supplyStackPartTwoList = new ArrayList<>();
    String line;
    boolean drawingDone = false;
    while ((line = br.readLine()) != null) {
      if (line.trim().length() > 0) {
        if (!drawingDone) {
          int crateIndex = 0;
          int startPosition = 0;
          while (startPosition + 3 <= line.length()) {
            String crate = line.substring(startPosition, startPosition + 3);
            if (crate.startsWith("[")) {
              for (int i = supplyStackList.size(); i < crateIndex + 1; i++) {
                supplyStackList.add(new Stack<>());
              }
              Stack<Character> crateStack = supplyStackList.get(crateIndex);

              if (crateStack == null) {
                crateStack = new Stack<>();
                supplyStackList.add(crateIndex, crateStack);
              }
              crateStack.add(0, crate.charAt(1));
//              System.out.println("Added " + crate.charAt(1) + " to bottom of stack " + crateIndex);
            }

            startPosition += 4;
            crateIndex++;
          }
        } else {
          // We are in the moving phase.
          String[] instructions = line.trim().split(" ");
          if (instructions.length < 6) {
            System.err.println("Instruction is less than six tokens: " + line.trim());
            continue;
          }

          int cratesToMove = Integer.parseInt(instructions[1]);
          int fromCrateNum = Integer.parseInt(instructions[3]);
          int toCrateNum = Integer.parseInt(instructions[5]);

          Stack<Character> fromCrateStack = supplyStackList.get(fromCrateNum - 1);
          Stack<Character> toCrateStack = supplyStackList.get(toCrateNum - 1);

          Stack<Character> fromCrateStackPartTwo = supplyStackPartTwoList.get(fromCrateNum - 1);
          Stack<Character> toCrateStackPartTwo = supplyStackPartTwoList.get(toCrateNum - 1);

          Stack<Character> intermediateStack = new Stack<>();
          for (int i = 0; i < cratesToMove; i++) {
            toCrateStack.push(fromCrateStack.pop());

            intermediateStack.push(fromCrateStackPartTwo.pop());
          }

          while(!intermediateStack.isEmpty()) {
            toCrateStackPartTwo.push(intermediateStack.pop());
          }
        }
      } else {
        drawingDone = true;
        supplyStackPartTwoList = new ArrayList<>(0);
        for (Stack<Character> characters : supplyStackList) {
          Stack<Character> newStack = (Stack<Character>) characters.clone();
          supplyStackPartTwoList.add(newStack);
        }
//        System.out.print("Top of each stack: ");
//        for (int i = 0; i < supplyStackList.size(); i++) {
//          System.out.print(supplyStackList.get(i).peek());
//        }
//        System.out.println();
      }
    }

    System.out.print("Message (Part 1): ");
    for (Stack<Character> characters : supplyStackList) {
      System.out.print(characters.peek());
    }
    System.out.println();

    System.out.print("Message (Part 2): ");
    for (Stack<Character> characters : supplyStackPartTwoList) {
      System.out.print(characters.peek());
    }
    System.out.println();
  }
}
