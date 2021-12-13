package com.deantee.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4Part2 {

  // 0 -> 3 - 7
  // 1 -> 9 - 13
  // 2 -> 15 - 19

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("inputs", "day-4.txt"));
      List<Integer> numbers = new ArrayList<>(Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList());
      List<BingoCard> bingoCards = new ArrayList<>();
      for (int i = 0; i <= (lines.size() - 4 - 3) / 6; i++) {
        List<String> bingoContent = lines.subList(i * 6 + 2, i * 6 + 2 + 5);
        bingoCards.add(new BingoCard(bingoContent));
      }
      int number = 0;
      while (bingoCards.stream().filter(bingoCard -> !bingoCard.isBingo()).toList().size() > 1) {
        number = numbers.remove(0);
        for (BingoCard card : bingoCards) {
          card.mark(number);
        }
      }
      BingoCard card = bingoCards.stream().filter(bingoCard -> !bingoCard.isBingo()).toList().get(0);
      while (!card.isBingo()) {
        number = numbers.remove(0);
        card.mark(number);
      }
      System.out.println(card.getScore(number));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class BingoCard {

    private final BingoNumber[][] card = new BingoNumber[5][5];

    public BingoCard(List<String> lines) {
      for (int i = 0; i < 5; i++) {
        String line = lines.get(i);
        List<Integer> numbers = Arrays.stream(line.split("\\s+")).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
        for (int j = 0; j < 5; j++) {
          card[i][j] = new BingoNumber(numbers.get(j));
        }
      }
    }

    public boolean isBingo() {
      for (int i = 0; i < 5; i++) {
        boolean b = true;
        for (int j = 0; j < 5; j++) {
          if (!card[i][j].marked) {
            b = false;
            break;
          }
        }
        if (b) return true;
      }
      for (int i = 0; i < 5; i++) {
        boolean b = true;
        for (int j = 0; j < 5; j++) {
          if (!card[j][i].marked) {
            b = false;
            break;
          }
        }
        if (b) return true;
      }
      return false;
    }

    public void mark(int number) {
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (card[i][j].number == number) {
            card[i][j].marked = true;
            return;
          }
        }
      }
    }

    public int getScore(int number) {
      int sum = 0;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (!card[i][j].marked) sum += card[i][j].number;
        }
      }
      return sum * number;
    }

    private static class BingoNumber {
      private final int number;
      private boolean marked = false;

      public BingoNumber(int number) {
        this.number = number;
      }
    }
  }
}
