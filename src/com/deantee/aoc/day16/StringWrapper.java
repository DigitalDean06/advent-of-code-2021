/*
 * https://adventofcode.com/2021/day/16
 */

package com.deantee.aoc.day16;

public class StringWrapper {
  private String string;

  public StringWrapper(String string) {
    if (string == null) throw new IllegalArgumentException("The string is null");
    this.string = string;
  }

  public String slice(int n) {
    String string = this.string.substring(0, n);
    this.string = this.string.substring(n);
    return string;
  }
}
