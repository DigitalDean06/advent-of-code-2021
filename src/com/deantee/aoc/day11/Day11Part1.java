/*
 * https://adventofcode.com/2021/day/11
 */

package com.deantee.aoc.day11;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day11Part1 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(11);
    int[][] map = new int[lines.get(0).length()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      for (int j = 0; j < line.length(); j++) {
        map[j][i] = Integer.parseInt(String.valueOf(line.charAt(j)));
      }
    }
    int n = 0;
    for (int i = 0; i < 100; i++) n += tick(map);
    System.out.println(n);
  }

  private static int tick(int[][] map) {
    for (int j = 0; j < map[0].length; j++) {
      for (int i = 0; i < map.length; i++) {
        ++map[i][j];
      }
    }
    List<Position> allFlashes = new ArrayList<>();
    List<Position> flashes;
    while (!(flashes = getFlashes(map)).isEmpty()) {
      allFlashes.addAll(flashes);
      for (Position flash : flashes) {
        map[flash.x()][flash.y()] = 0;
        for (int j = -1; j <= 1; j++) {
          for (int i = -1; i <= 1; i++) {
            int a = flash.x() + i;
            int b = flash.y() + j;
            if (allFlashes.contains(new Position(a, b)) || a < 0 || a >= map.length || b < 0 || b >= map[0].length)
              continue;
            ++map[a][b];
          }
        }
      }
    }
    return allFlashes.size();
  }

  private static List<Position> getFlashes(int[][] map) {
    List<Position> flashes = new ArrayList<>();
    for (int j = 0; j < map[0].length; j++)
      for (int i = map.length - 1; i >= 0; i--) if (map[i][j] >= 10) flashes.add(new Position(i, j));
    return flashes;
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
