/*
 * https://adventofcode.com/2021/day/7
 */

package com.deantee.aoc.day07;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day07Part2 {

  public static void main(String[] args) throws IOException {
    List<Integer> integers = Arrays.stream(AOC.get(7).get(0).split(",")).map(Integer::parseInt).toList();
    Integer min = null;
    Integer max = null;
    for (int i : integers) {
      if (min == null || min > i) min = i;
      if (max == null || max < i) max = i;
    }
    if (min == null) throw new IllegalArgumentException("Empty input");
    Integer minCost = null;
    for (int i = min; i <= max; i++) {
      int sum = 0;
      for (int j : integers) {
        int k = Math.abs(j - i);
        sum += k * (1 + k) / 2;
      }
      if (minCost == null || minCost > sum) minCost = sum;
    }
    System.out.println(minCost);
  }
}
