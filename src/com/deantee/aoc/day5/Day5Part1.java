package com.deantee.aoc.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day5Part1 {

  public static void main(String[] args) {
    try {
      Map<Position, Integer> posMap = new HashMap<>();
      List<String> lines = Files.readAllLines(Paths.get("inputs", "day-5.txt"));
      for (String line : lines) {
        String[] coords = line.split(" -> ");
        String[] from = coords[0].split(",");
        String[] to = coords[1].split(",");
        int x1 = Integer.parseInt(from[0]);
        int y1 = Integer.parseInt(from[1]);
        int x2 = Integer.parseInt(to[0]);
        int y2 = Integer.parseInt(to[1]);
        if (x1 > x2) {
          int temp = x1;
          x1 = x2;
          x2 = temp;
        }
        if (y1 > y2) {
          int temp = y1;
          y1 = y2;
          y2 = temp;
        }
        if (x1 != x2 && y1 != y2) continue;
        for (int x = x1; x <= x2; x++) {
          for (int y = y1; y <= y2; y++) {
            posMap.put(new Position(x, y), posMap.getOrDefault(new Position(x, y), 0) + 1);
          }
        }
      }
      int n = 0;
      for (int i : posMap.values()) {
        if (i >= 2) ++n;
      }
      System.out.println(n);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static record Position(int x, int y) {
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Position position = (Position) o;
      return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
