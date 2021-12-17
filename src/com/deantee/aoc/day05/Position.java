package com.deantee.aoc.day05;

import java.util.Objects;

public class Position {
  private final int x;
  private final int y;
  private int amount = 1;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void incrementAmount() {
    ++amount;
  }

  public int getAmount() {
    return amount;
  }

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
