package com.deantee.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day1Part2 {

  public static void main(String[] args) {
    try {
      Map<Integer, Integer> map = new LinkedHashMap<>();
      List<Integer> inputs = Files.readAllLines(Paths.get("inputs", "day-1.txt")).stream().map(Integer::parseInt).toList();
      for (int i = 0; i < inputs.size() - 2; i++) {
        map.put(i, inputs.get(i) + inputs.get(i + 1) + inputs.get(i + 2));
      }
      Integer last = null;
      int n = 0;
      for (int i : map.values().stream().toList()) {
        if (last != null && last < i) ++n;
        last = i;
      }
      System.out.println(n);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
