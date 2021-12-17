package com.deantee.aoc.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day2Part2 {

  public static void main(String[] args) {
    int horizontal = 0;
    int depth = 0;
    int aim = 0;
    try {
      for (String line : Files.readAllLines(Paths.get("inputs", "day-2.txt"))) {
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
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(horizontal * depth);
  }
}