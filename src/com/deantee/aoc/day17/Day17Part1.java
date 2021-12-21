/*
 * https://adventofcode.com/2021/day/17
 */

package com.deantee.aoc.day17;

import com.deantee.aoc.util.AOC;

import java.io.IOException;

public class Day17Part1 {

  public static void main(String[] args) throws IOException {
    int y1 = Integer.parseInt(AOC.get(17).get(0).split(": ")[1].split(", ")[1].split("=")[1].split("\\.\\.")[0]);
    int n = -y1 - 1;
    System.out.println(n * (n + 1) / 2);
  }
}
