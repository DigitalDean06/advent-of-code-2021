/*
 * https://adventofcode.com/2021/day/16
 */

package com.deantee.aoc.day16;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day16Part2 {

  public static void main(String[] args) throws IOException {
    StringWrapper string = new StringWrapper(toBinaryString(AOC.get(16).get(0)));
    System.out.println(readPacket(string).value);
  }

  private static PacketResult readPacket(StringWrapper string) {
    int version = Integer.parseInt(string.slice(3), 2);
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
      return new PacketResult(n, value);
    } else {
      int lengthTypeID = Integer.parseInt(string.slice(1), 2);
      ++n;
      List<Long> values = new ArrayList<>();
      if (lengthTypeID == 0) {
        int totalLengthInBits = Integer.parseInt(string.slice(15), 2);
        n += 15;
        int m = 0;
        while (m != totalLengthInBits) {
          PacketResult result = readPacket(string);
          m += result.bitsReaded;
          values.add(result.value);
        }
        n += m;
      } else {
        int numberOfSubpackets = Integer.parseInt(string.slice(11), 2);
        n += 11;
        for (int i = 0; i < numberOfSubpackets; i++) {
          PacketResult result = readPacket(string);
          values.add(result.value);
          n += result.bitsReaded;
        }
      }
      return switch (typeID) {
        case 0 -> new PacketResult(n, values.stream().reduce(0L, Long::sum));
        case 1 -> new PacketResult(n, values.stream().reduce(1L, (aLong, aLong2) -> aLong * aLong2));
        case 2 -> new PacketResult(n, Collections.min(values));
        case 3 -> new PacketResult(n, Collections.max(values));
        case 5 -> new PacketResult(n, values.get(0) > values.get(1) ? 1 : 0);
        case 6 -> new PacketResult(n, values.get(0) < values.get(1) ? 1 : 0);
        case 7 -> new PacketResult(n, Objects.equals(values.get(0), values.get(1)) ? 1 : 0);
        default -> throw new IllegalArgumentException("Invalid packet");
      };
    }
  }

  private static String toBinaryString(String string) {
    StringBuilder stringBuilder = new StringBuilder();
    for (char c : string.toCharArray()) {
      stringBuilder.append(String.format("%4s", Integer.toBinaryString(Integer.parseInt(String.valueOf(c), 16))).replaceAll(" ", "0"));
    }
    return stringBuilder.toString();
  }
}
