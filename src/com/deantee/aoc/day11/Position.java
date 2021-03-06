/*
 * https://adventofcode.com/2021/day/11
 */

package com.deantee.aoc.day11;

import java.util.Objects;

public record Position(int x, int y) {
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