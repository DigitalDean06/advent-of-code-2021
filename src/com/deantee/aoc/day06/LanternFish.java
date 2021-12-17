package com.deantee.aoc.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanternFish {
  private long amount;
  private final int timer;

  private LanternFish(int timer, long amount) {
    this.timer = timer;
    this.amount = amount;
  }

  public static void create(int timer, List<LanternFish> fishList) {
    create(timer, fishList, 1);
  }

  private static void create(int timer, List<LanternFish> fishList, long amount) {
    Optional<LanternFish> optional = fishList.stream().filter(lanternFish -> lanternFish.timer == timer).findFirst();
    if (optional.isPresent()) {
      optional.get().amount += amount;
    } else {
      fishList.add(new LanternFish(timer, amount));
    }
  }

  public static void tickAll(List<LanternFish> fishList) {
    List<LanternFish> temp = new ArrayList<>(fishList);
    fishList.clear();
    temp.forEach(lanternFish -> lanternFish.tick(fishList));
  }

  public void tick(List<LanternFish> fishList) {
    if (timer - 1 < 0) {
      create(6, fishList, amount);
      create(8, fishList, amount);
    } else {
      create(timer - 1, fishList, amount);
    }
  }

  public long getAmount() {
    return amount;
  }

  public int getTimer() {
    return timer;
  }
}
