/*
 * https://adventofcode.com/2021/day/8
 */

package com.deantee.aoc.day08;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Day08Part1 {

  public static void main(String[] args) throws IOException {
    AtomicInteger n = new AtomicInteger();
    AOC.get(8).forEach(s -> Arrays.stream(s.split(" \\| ")[1].split(" ")).toList().forEach(s1 -> {
      if (s1.length() == 2 || s1.length() == 3 || s1.length() == 4 || s1.length() == 7) n.incrementAndGet();
    }));
    System.out.println(n.get());
  }
}
