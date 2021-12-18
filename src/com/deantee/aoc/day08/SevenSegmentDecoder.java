/*
 * https://adventofcode.com/2021/day/8
 */

package com.deantee.aoc.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SevenSegmentDecoder {

  private final Map<Integer, List<Character>> map = new HashMap<>();

  public SevenSegmentDecoder(List<String> inputs) {
    inputs = new ArrayList<>(inputs);
    if (inputs.size() != 10) throw new IllegalArgumentException("Invalid inputs");
    for (String input : new ArrayList<>(inputs)) {
      boolean b = true;
      switch (input.length()) {
        case 2 -> map.put(1, toCharacterList(input));
        case 3 -> map.put(7, toCharacterList(input));
        case 4 -> map.put(4, toCharacterList(input));
        case 7 -> map.put(8, toCharacterList(input));
        default -> b = false;
      }
      if (b) inputs.remove(input);
    }
    if (!map.containsKey(1) || !map.containsKey(7) || !map.containsKey(4) || !map.containsKey(8))
      throw new IllegalArgumentException("Invalid inputs");
    List<Character> side = map.get(1);
    List<Character> temp = map.get(7).stream().filter(character -> !map.get(1).contains(character)).toList();
    if (temp.size() != 1) throw new IllegalArgumentException("Invalid inputs");
    char top = temp.get(0);
    temp = map.get(4).stream().filter(character -> !map.get(1).contains(character)).toList();
    if (temp.size() != 2) throw new IllegalArgumentException("Invalid inputs");
    List<Character> topLeft = temp;
    temp = map.get(8).stream().filter(character -> character != top && !side.contains(character) && !topLeft.contains(character)).toList();
    if (temp.size() != 2) throw new IllegalArgumentException("Invalid inputs");
    List<Character> bottomLeft = temp;
    for (String input : inputs) {
      int tl = countMatches(input, topLeft);
      int bl = countMatches(input, bottomLeft);
      int s = countMatches(input, side);
      Integer i = null;
      switch (input.length()) {
        case 5 -> {
          if (tl == 1) {
            if (s == 1) i = 2;
            else if (bl == 1) i = 3;
          } else if (bl == 1 && s == 1) i = 5;
        }
        case 6 -> {
          if (tl == 1) i = 0;
          else if (s == 1) i = 6;
          else if (bl == 1) i = 9;
        }
      }
      if (i == null) throw new IllegalArgumentException("Invalid inputs");
      map.put(i, toCharacterList(input));
    }
  }

  public int decode(String input) {
    List<Character> characters = toCharacterList(input);
    List<Integer> keys = map.keySet().stream().filter(integer -> map.get(integer).size() == characters.size()).toList();
    if (keys.isEmpty()) throw new IllegalArgumentException("Invalid input");
    for (int key : keys) {
      if (map.get(key).containsAll(characters)) return key;
    }
    throw new IllegalArgumentException("Invalid input");
  }

  private static List<Character> toCharacterList(String string) {
    return string.chars().mapToObj(value -> (char) value).toList();
  }

  private static int countMatches(String string, List<Character> characters) {
    int n = 0;
    for (char c : string.toCharArray()) {
      if (characters.contains(c)) ++n;
    }
    return n;
  }
}
