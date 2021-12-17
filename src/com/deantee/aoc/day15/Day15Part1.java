package com.deantee.aoc.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day15Part1 {

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("inputs", "day-15.txt"));
      int[][] map = new int[lines.get(0).length()][lines.size()];
      for (int i = 0; i < lines.size(); i++) {
        for (int j = 0; j < lines.get(i).length(); j++) {
          map[j][i] = Integer.parseInt(String.valueOf(lines.get(i).charAt(j)));
        }
      }
      System.out.println(brute(map, 0, 0, 0) - map[0][0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static final Map<Position, Integer> bruteResults = new HashMap<>();

  public static int brute(int[][] map, int x, int y, int n) {
    Position key = new Position(x, y);
    if (bruteResults.containsKey(key)) return bruteResults.get(key);
    if (x == map[0].length - 1 || y == map.length - 1) {
      if (x == map[0].length - 1 && y == map.length - 1) {
        int i = n + map[x][y];
        bruteResults.put(key, i);
        return i;
      } else if (x == map[0].length - 1) {
        return brute(map, x, y + 1, n) + map[x][y];
      } else {
        return brute(map, x + 1, y, n) + map[x][y];
      }
    } else {
      int a = brute(map, x + 1, y, n);
      int b = brute(map, x, y + 1, n);
      int i = Math.min(a, b) + map[x][y];
      bruteResults.put(key, i);
      return i;
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
