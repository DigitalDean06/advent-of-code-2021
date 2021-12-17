package com.deantee.aoc.day04;

import java.util.Arrays;
import java.util.List;

public class BingoCard {

  private final BingoNumber[][] numbers = new BingoNumber[5][5];
  private int score = 0;
  private int lastInput = 0;

  public BingoCard(List<String> inputs) {
    if (inputs.size() != 5 || inputs.stream().anyMatch(input -> Arrays.stream(input.split("\\s+")).filter(s -> !s.isBlank()).toList().size() != 5))
      throw new IllegalArgumentException("Invalid inputs");
    for (int i = 0; i < 5; i++) {
      String input = inputs.get(i);
      List<Integer> args = Arrays.stream(input.split("\\s+")).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
      for (int j = 0; j < args.size(); j++) {
        numbers[j][i] = new BingoNumber(args.get(j));
      }
    }
  }

  public void mark(int input) {
    if (isBingo()) return;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        BingoNumber number = numbers[i][j];
        if (number.number != input) continue;
        if (number.marked) return;
        number.marked = true;
        lastInput = input;
        if (isBingo()) score = calculateScore();
        return;
      }
    }
  }

  public boolean isBingo() {
    for (int i = 0; i < 5; i++) {
      boolean b = true;
      boolean b2 = true;
      for (int j = 0; j < 5; j++) {
        if (!numbers[i][j].marked) {
          b = false;
        }
        if (!numbers[j][i].marked) {
          b2 = false;
        }
      }
      if (b || b2) return true;
    }
    return false;
  }

  public int calculateScore() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        BingoNumber number = numbers[i][j];
        if (!number.marked) sum += number.number;
      }
    }
    return sum * lastInput;
  }

  public int getScore() {
    return score;
  }

  private static class BingoNumber {

    private final int number;
    private boolean marked = false;

    private BingoNumber(int number) {
      this.number = number;
    }
  }
}
