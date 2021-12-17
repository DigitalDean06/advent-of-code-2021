package com.deantee.aoc.day02;

import com.deantee.aoc.util.AOC;

import java.io.IOException;

public class Day02Part2 {

  public static void main(String[] args) throws IOException {
    int horizontal = 0;
    int depth = 0;
    int aim = 0;
    for (String line : AOC.get(2)) {
      String[] parameters = line.split(" ");
      String action = parameters[0];
      int value = Integer.parseInt(parameters[1]);
      switch (action) {
        case "forward" -> {
          horizontal += value;
          depth += aim * value;
        }
        case "down" -> aim += value;
        case "up" -> aim -= value;
        default -> throw new IllegalArgumentException("Invalid action");
      }
    }
    System.out.println(horizontal * depth);
  }
}
