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

  public boolean isSmallCave() {
    for (char c : name.toCharArray())
      if (!Character.isLowerCase(c)) return false;
    return true;
  }

  public int brute(List<Cave> visitedCaves) {
    visitedCaves.add(this);
    int n = 0;
    for (Cave connectedCave : connectedCaves) {
      if (visitedCaves.contains(connectedCave) && connectedCave.isSmallCave()) continue;
      if (connectedCave.name.equals("end")) n += 1;
      else n += connectedCave.brute(new ArrayList<>(visitedCaves));
    }
    return n;
  }

  public int brute2(Map<Cave, Integer> visitedCaves) {
    visitedCaves.put(this, visitedCaves.getOrDefault(this, 0) + 1);
    int n = 0;
    for (Cave connectedCave : connectedCaves) {
      if (connectedCave.name.equals("start")) continue;
      if (visitedCaves.getOrDefault(connectedCave, 0) >= 1 && connectedCave.isSmallCave() && containRevisitedSmallCave(visitedCaves)) continue;
      if (connectedCave.name.equals("end")) n += 1;
      else n += connectedCave.brute2(new HashMap<>(visitedCaves));
    }
    return n;
  }

  private boolean containRevisitedSmallCave(Map<Cave, Integer> visitedCaves) {
    for (Cave cave : visitedCaves.keySet()) {
      if (!cave.isSmallCave()) continue;
      if (visitedCaves.get(cave) < 2) continue;
      return true;
    }
    return false;
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
