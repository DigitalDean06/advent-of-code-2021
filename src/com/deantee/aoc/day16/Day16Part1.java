/*
 * https://adventofcode.com/2021/day/16
 */

package com.deantee.aoc.day16;

import com.deantee.aoc.util.AOC;

import java.io.IOException;

public class Day16Part1 {

  private static int versionSum = 0;

  public static void main(String[] args) throws IOException {
    StringWrapper string = new StringWrapper(toBinaryString(AOC.get(16).get(0)));
    readPacket(string);
    System.out.println(versionSum);
  }

  private static int readPacket(StringWrapper string) {
    int version = Integer.parseInt(string.slice(3), 2);
    versionSum += version;
    int typeID = Integer.parseInt(string.slice(3), 2);
    int n = 6;
    if (typeID == 4) {
      StringBuilder stringBuilder = new StringBuilder();
      while (true) {
        String s = string.slice(5);
        n += 5;
        stringBuilder.append(s, 1, 5);
        if (s.startsWith("0")) break;
      }
      long value = Long.parseLong(stringBuilder.toString(), 2);
    } else {
      int lengthTypeID = Integer.parseInt(string.slice(1), 2);
      ++n;
      if (lengthTypeID == 0) {
        int totalLengthInBits = Integer.parseInt(string.slice(15), 2);
        n += 15;
        int m = 0;
        while (m != totalLengthInBits) {
          m += readPacket(string);
        }
        n += m;
      } else {
        int numberOfSubpackets = Integer.parseInt(string.slice(11), 2);
        n += 11;
        for (int i = 0; i < numberOfSubpackets; i++) {
          n += readPacket(string);
        }
      }
    }
    return n;
  }

  private static String toBinaryString(String string) {
    StringBuilder stringBuilder = new StringBuilder();
    for (char c : string.toCharArray()) {
      stringBuilder.append(String.format("%4s", Integer.toBinaryString(Integer.parseInt(String.valueOf(c), 16))).replaceAll(" ", "0"));
    }
    return stringBuilder.toString();
  }
}
