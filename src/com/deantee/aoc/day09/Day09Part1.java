/*
 * https://adventofcode.com/2021/day/9
 */

package com.deantee.aoc.day09;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.List;

public class Day09Part1 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(9);
    int[][] map = new int[lines.get(0).length()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      for (int j = 0; j < line.length(); j++) {
        map[j][i] = Integer.parseInt(String.valueOf(line.charAt(j)));
      }
    }
    int sum = 0;
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[0].length; y++) {
        int current = map[x][y];
        boolean b = x == 0 || map[x - 1][y] > current;
        if (y != 0 && map[x][y - 1] <= current) b = false;
        if (x != map.length - 1 && map[x + 1][y] <= current) b = false;
        if (y != map[0].length - 1 && map[x][y + 1] <= current) b = false;
        if (b) sum += current + 1;
      }
    }
    System.out.println(sum);
  }
}
