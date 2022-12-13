package org.aoc.tmousaw.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Packet {

  private List<Object> data;

  public Packet(String input) {
    assert input.charAt(0) == '[' && input.charAt(input.length() - 1) == ']';
    Stack<List<Object>> listStack = new Stack<>();
    String tempString = "";
    data = null;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '[') {
        List<Object> newList = new ArrayList<>();
        if (data == null) {
          data = newList;
        } else {
          listStack.peek().add(newList);
        }
        listStack.push(newList);
      } else if (input.charAt(i) == ']') {
        if (!tempString.isEmpty()) {
          listStack.peek().add(Integer.parseInt(tempString));
          tempString = "";
        }
        listStack.pop();
      } else if (input.charAt(i) == ',') {
        if (!tempString.isEmpty()) {
          listStack.peek().add(Integer.parseInt(tempString));
          tempString = "";
        }
      } else {
        tempString = tempString + input.charAt(i);
      }
    }
  }

  public List<Object> getData() {
    return data;
  }
}
