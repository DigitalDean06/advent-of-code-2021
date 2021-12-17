package com.deantee.aoc.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1Part1 {

  public static void main(String[] args) {
    Integer last = null;
    int n = 0;
    try {
      for (int i : Files.readAllLines(Paths.get("inputs", "day-1.txt")).stream().map(Integer::parseInt).toList()) {
        if (last != null && last < i) ++n;
        last = i;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(n);
  }
}
