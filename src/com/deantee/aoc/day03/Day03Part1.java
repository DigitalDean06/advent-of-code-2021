/*
 * https://adventofcode.com/2021/day/3
 */

package com.deantee.aoc.day03;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.List;

public class Day03Part1 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(3);
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < lines.get(0).length(); i++) {
      int zeroAmount = 0;
      int oneAmount = 0;
      for (String line : lines) {
        if (line.charAt(i) == '0') ++zeroAmount;
        else ++oneAmount;
      }
      stringBuilder.append(zeroAmount > oneAmount ? "0" : "1");
    }
    int gammaRate = Integer.parseInt(stringBuilder.toString(), 2);
    int epsilonRate = gammaRate ^ Integer.parseInt("1".repeat(lines.get(0).length()), 2);
    System.out.println(gammaRate * epsilonRate);
  }
}
