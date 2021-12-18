/*
 * https://adventofcode.com/2021/day/15
 */

package com.deantee.aoc.day15;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15Part1 {

  private static final Map<Position, Integer> cache = new HashMap<>();

  public static void main(String[] args) {
    try {
      List<String> lines = AOC.get(15);
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

  public static int brute(int[][] map, int x, int y, int n) {
    Position position = new Position(x, y);
    if (cache.containsKey(position)) return cache.get(position) + n;
    if (x == map.length - 1 || y == map[0].length - 1) {
      if (x == map.length - 1 && y == map[0].length - 1) {
        return n + map[x][y];
      } else if (x == map.length - 1) {
        int i = brute(map, x, y + 1, map[x][y]);
        cache.put(position, i);
        return i + n;
      } else {
        int i = brute(map, x + 1, y, map[x][y]);
        cache.put(position, i);
        return i + n;
      }
    } else {
      int a = brute(map, x + 1, y, map[x][y]);
      int b = brute(map, x, y + 1, map[x][y]);
      int i = Math.min(a, b);
      cache.put(position, i);
      return i + n;
    }
  }
}
