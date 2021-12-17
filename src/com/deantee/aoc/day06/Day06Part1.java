package com.deantee.aoc.day06;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06Part1 {

  public static void main(String[] args) throws IOException {
    List<LanternFish> fishList = new ArrayList<>();
    Arrays.stream(AOC.get(6).get(0).split(",")).map(Integer::parseInt).toList().forEach(integer -> LanternFish.create(integer, fishList));
    for (int i = 0; i < 80; i++) {
      for (LanternFish fish : fishList) {
        System.out.println(fish.getTimer() + " -> " + fish.getAmount());
      }
      System.out.println();
      LanternFish.tickAll(fishList);
    }
    fishList.stream().map(LanternFish::getAmount).reduce(Long::sum).ifPresent(System.out::println);
  }
}
