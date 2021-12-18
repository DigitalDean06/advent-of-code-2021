/*
 * https://adventofcode.com/2021/day/15
 */

package com.deantee.aoc.day15;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15Part2 {

  private static final Map<Position, Integer> cache = new HashMap<>();

  public static void main(String[] args) {
    try {
      List<String> lines = AOC.get(15);
      int[][] map = new int[lines.get(0).length() * 5][lines.size() * 5];
      for (int a = 0; a < 5; a++) {
        for (int b = 0; b < 5; b++) {
          for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
              map[j + a * lines.get(i).length()][i + b * lines.size()] = wrap(Integer.parseInt(String.valueOf(lines.get(i).charAt(j))) + a + b);
            }
          }
        }
      }
      System.out.println(brute(map, 0, 0, 0) - map[0][0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static int wrap(int i) {
    return i / 10 + i % 10;
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
