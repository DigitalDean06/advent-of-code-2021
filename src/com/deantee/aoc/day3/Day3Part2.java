package com.deantee.aoc.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3Part2 {

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("inputs", "day-3.txt"));
      List<String> oxygenRating = new ArrayList<>(lines);
      List<String> scrubberRating = new ArrayList<>(lines);
      int size = lines.get(0).length();
      for (int i = 0; i < size && oxygenRating.size() > 1; i++) {
        int zeroCount = 0;
        for (String line : oxygenRating) {
          if (line.charAt(i) == '0') ++zeroCount;
        }
        int finalI = i;
        int finalZeroCount = zeroCount;
        oxygenRating.removeAll(oxygenRating.stream().filter(s -> s.charAt(finalI) != (finalZeroCount > oxygenRating.size() / 2 ? '0' : '1')).toList());
      }
      for (int i = 0; i < size && scrubberRating.size() > 1; i++) {
        int zeroCount = 0;
        for (String line : scrubberRating) {
          if (line.charAt(i) == '0') ++zeroCount;
        }
        int finalI = i;
        int finalZeroCount = zeroCount;
        scrubberRating.removeAll(scrubberRating.stream().filter(s -> s.charAt(finalI) != (finalZeroCount > scrubberRating.size() / 2 ? '1' : '0')).toList());
      }
      System.out.println(Integer.parseInt(oxygenRating.stream().reduce("", (s, s2) -> s + s2), 2) * Integer.parseInt(scrubberRating.stream().reduce("", (s, s2) -> s + s2), 2));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
