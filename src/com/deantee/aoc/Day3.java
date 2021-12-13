package com.deantee.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {

  public static void main(String[] args) {
    try {
      List<String> strings = Files.readAllLines(Paths.get("inputs", "day-3.txt"));
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < strings.get(0).length(); i++) {
        int count = 0;
        for (String string : strings) {
          if (string.charAt(i) == '0') ++count;
        }
        stringBuilder.append(count > strings.size() / 2 ? "0" : "1");
      }
      int gammaRate = Integer.parseInt(stringBuilder.toString(), 2);
      int epsilonRate = gammaRate ^ Integer.parseInt("1".repeat(strings.get(0).length()), 2);
      System.out.println(gammaRate * epsilonRate);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
