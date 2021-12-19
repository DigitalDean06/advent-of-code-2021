/*
 * https://adventofcode.com/2021/day/12
 */

package com.deantee.aoc.day12;

import java.util.*;

public class Cave {
  public final String name;
  private final Set<Cave> connectedCaves = new HashSet<>();

  public Cave(String name) {
    this.name = name;
  }

  public void connect(Cave cave) {
    connectedCaves.add(cave);
    cave.connectedCaves.add(this);
  }

  private boolean isSmallCave() {
    for (char c : name.toCharArray())
      if (!Character.isLowerCase(c)) return false;
    return true;
  }

  public int brute(List<Cave> visitedCave) {
    visitedCave.add(this);
    int n = 0;
    for (Cave connectedCave : connectedCaves) {
      if (visitedCave.contains(connectedCave) && connectedCave.isSmallCave()) continue;
      if (connectedCave.name.equals("end")) n += 1;
      else n += connectedCave.brute(new ArrayList<>(visitedCave));
    }
    return n;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cave cave = (Cave) o;
    return Objects.equals(name, cave.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
