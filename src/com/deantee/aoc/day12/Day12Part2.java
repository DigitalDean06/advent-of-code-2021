/*
 * https://adventofcode.com/2021/day/12
 */

package com.deantee.aoc.day12;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day12Part2 {

  public static void main(String[] args) throws IOException {
    List<Cave> caveList = new ArrayList<>();
    for (String line : AOC.get(12)) {
      String[] arguments = line.split("-");
      Cave cave1 = new Cave(arguments[0]);
      Cave cave2 = new Cave(arguments[1]);
      if (caveList.contains(cave1)) cave1 = caveList.get(caveList.indexOf(cave1));
      else caveList.add(cave1);
      if (caveList.contains(cave2)) cave2 = caveList.get(caveList.indexOf(cave2));
      else caveList.add(cave2);
      cave1.connect(cave2);
    }
    Cave start = new Cave("start");
    if (!caveList.contains(start)) throw new IllegalArgumentException("Invalid inputs");
    start = caveList.get(caveList.indexOf(start));
    System.out.println(start.brute2(new HashMap<>()));
  }
}
