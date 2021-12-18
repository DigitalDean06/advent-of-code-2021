/*
 * https://adventofcode.com/2021/day/1
 */

package com.deantee.aoc.day01;

import com.deantee.aoc.util.AOC;

import java.io.IOException;

public class Day01Part1 {

  public static void main(String[] args) throws IOException {
    Integer last = null;
    int n = 0;
    for (int i : AOC.get(1).stream().map(Integer::parseInt).toList()) {
      if (last != null && last < i) ++n;
      last = i;
    }
    System.out.println(n);
  }
}
