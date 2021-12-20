/*
 * https://adventofcode.com/2021/day/14
 */

package com.deantee.aoc.day14;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14Part1 {

  private static final Map<String, String> mapping = new HashMap<>();

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(14);
    String original = lines.get(0);
    for (String mappingString : lines.subList(2, lines.size())) {
      String[] mappingArgs = mappingString.split(" -> ");
      mapping.put(mappingArgs[0], mappingArgs[1]);
    }
    for (int i = 0; i < 10; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int j = 0; j < original.length() - 1; j++) {
        stringBuilder.append(original.charAt(j)).append(mapping.get(String.valueOf(original.charAt(j)) + original.charAt(j + 1)));
      }
      original = stringBuilder.append(original.charAt(original.length() - 1)).toString();
    }
    Map<Character, Integer> counter = new HashMap<>();
    for (char c : original.toCharArray()) {
      counter.put(c, counter.getOrDefault(c, 0) + 1);
    }
    List<Integer> sortedValues = counter.values().stream().sorted(Comparator.comparingInt(o -> o)).toList();
    System.out.println(sortedValues.get(sortedValues.size() - 1) - sortedValues.get(0));
  }
}
