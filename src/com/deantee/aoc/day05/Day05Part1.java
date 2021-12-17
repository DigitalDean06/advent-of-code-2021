package com.deantee.aoc.day05;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day05Part1 {

  public static void main(String[] args) throws IOException {
    List<Position> positions = new ArrayList<>();
    List<String> lines = AOC.get(5);
    for (String line : lines) {
      String[] coords = line.split(" -> ");
      String[] from = coords[0].split(",");
      String[] to = coords[1].split(",");
      int x1 = Integer.parseInt(from[0]);
      int y1 = Integer.parseInt(from[1]);
      int x2 = Integer.parseInt(to[0]);
      int y2 = Integer.parseInt(to[1]);
      if (x1 != x2 && y1 != y2) continue;
      for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
          Position newPosition = new Position(x, y);
          if (positions.contains(newPosition)) positions.get(positions.indexOf(newPosition)).incrementAmount();
          else positions.add(newPosition);
        }
      }
    }
    int n = 0;
    for (Position position : positions) {
      if (position.getAmount() >= 2) ++n;
    }
    System.out.println(n);
  }
}
