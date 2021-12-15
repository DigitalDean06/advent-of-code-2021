package com.deantee.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

  public static void main(String[] args) {
    try {
      List<LanternFish> fishList = new ArrayList<>();
      Arrays.stream(Files.readAllLines(Paths.get("inputs", "day-6.txt")).get(0).split(",")).map(Integer::parseInt).forEach(integer -> {
        fishList.add(new LanternFish(integer));
      });
      for (int i = 0; i < 80; i++) {
        List<LanternFish> toAdd = new ArrayList<>();
        fishList.forEach(lanternFish -> {
          LanternFish fish;
          if ((fish = lanternFish.tick()) != null) toAdd.add(fish);
        });
        fishList.addAll(toAdd);
      }
      System.out.println(fishList.size());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class LanternFish {
    public int timer;

    public LanternFish(int timer) {
      this.timer = timer;
    }

    public LanternFish tick() {
      if (--timer < 0) {
        timer = 6;
        return new LanternFish(8);
      }
      return null;
    }
  }
}
