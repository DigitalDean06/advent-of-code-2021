package com.deantee.aoc.day08;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.Arrays;

public class Day08Part2 {

  public static void main(String[] args) throws IOException {
    int sum = 0;
    for (String line : AOC.get(8)) {
      String[] arguments = line.split(" \\| ");
      SevenSegmentDecoder decoder = new SevenSegmentDecoder(Arrays.asList(arguments[0].split(" ")));
      String[] cipher = arguments[1].split(" ");
      int number = 0;
      for (int i = 0; i < cipher.length; i++) {
        number += decoder.decode(cipher[i]) * Math.pow(10, cipher.length - i - 1);
      }
      sum += number;
    }
    System.out.println(sum);
  }
}
