/*
 * https://adventofcode.com/2021/day/16
 */

package com.deantee.aoc.day16;

public class PacketResult {

  public int bitsReaded;
  public long value;

  public PacketResult(int bitsReaded, long value) {
    this.bitsReaded = bitsReaded;
    this.value = value;
  }
}
