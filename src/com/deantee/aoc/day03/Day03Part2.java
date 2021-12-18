/*
 * https://adventofcode.com/2021/day/3
 */

package com.deantee.aoc.day03;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03Part2 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(3);
    List<String> oxygenRating = new ArrayList<>(lines);
    List<String> scrubberRating = new ArrayList<>(lines);
    int size = lines.get(0).length();
    for (int i = 0; i < size && oxygenRating.size() > 1; i++) {
      int zeroAmount = 0;
      int oneAmount = 0;
      for (String line : oxygenRating) {
        if (line.charAt(i) == '0') ++zeroAmount;
        else ++oneAmount;
      }
      final int finalI = i;
      final int finalZeroAmount = zeroAmount;
      final int finalOneAmount = oneAmount;
      oxygenRating.removeAll(oxygenRating.stream().filter(s -> s.charAt(finalI) != (finalZeroAmount > finalOneAmount ? '0' : '1')).toList());
    }
    for (int i = 0; i < size && scrubberRating.size() > 1; i++) {
      int zeroAmount = 0;
      int oneAmount = 0;
      for (String line : scrubberRating) {
        if (line.charAt(i) == '0') ++zeroAmount;
        else ++oneAmount;
      }
      int finalI = i;
      int finalZeroAmount = zeroAmount;
      int finalOneAmount = oneAmount;
      scrubberRating.removeAll(scrubberRating.stream().filter(s -> s.charAt(finalI) == (finalZeroAmount > finalOneAmount ? '0' : '1')).toList());
    }
    System.out.println(Integer.parseInt(oxygenRating.get(0), 2) * Integer.parseInt(scrubberRating.get(0), 2));
  }
}
