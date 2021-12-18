/*
 * https://adventofcode.com/2021/day/9
 */

package com.deantee.aoc.day09;

public class PositionHeight {
  public final int height;
  private boolean marked = false;

  public PositionHeight(int height) {
    this.height = height;
  }

  public boolean isMarked() {
    return marked;
  }

  public void mark() {
    marked = true;
  }
}
