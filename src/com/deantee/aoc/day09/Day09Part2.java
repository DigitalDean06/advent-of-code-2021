/*
 * https://adventofcode.com/2021/day/9
 */

package com.deantee.aoc.day09;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day09Part2 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(9);
    PositionHeight[][] map = new PositionHeight[lines.get(0).length()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      char[] chars = line.toCharArray();
      for (int j = 0; j < chars.length; j++) {
        char c = chars[j];
        map[j][i] = new PositionHeight(Integer.parseInt(String.valueOf(c)));
      }
    }
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        int k = propagate(map, i, j);
        if (k != 0) list.add(k);
      }
    }
    if (list.size() < 3) throw new IllegalArgumentException("Invalid inputs");
    list.sort((o1, o2) -> o2 - o1);
    System.out.println(list.get(0) * list.get(1) * list.get(2));
  }

  private static int propagate(PositionHeight[][] map, int x, int y) {
    if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return 0;
    if (map[x][y].height == 9) return 0;
    if (map[x][y].isMarked()) return 0;
    map[x][y].mark();
    return 1 + propagate(map, x - 1, y) + propagate(map, x, y - 1) + propagate(map, x + 1, y) + propagate(map, x, y + 1);
  }
}
