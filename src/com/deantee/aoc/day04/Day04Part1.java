package com.deantee.aoc.day04;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04Part1 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(4);
    List<Integer> numbers = new ArrayList<>(Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList());
    List<BingoCard> cards = new ArrayList<>();
    for (int i = 0; i <= (lines.size() - 4 - 3) / 6; i++) {
      List<String> inputs = lines.subList(i * 6 + 2, i * 6 + 2 + 5);
      cards.add(new BingoCard(inputs));
    }
    BingoCard bingoedCard = null;
    do {
      int number = numbers.remove(0);
      for (BingoCard card : new ArrayList<>(cards)) {
        card.mark(number);
        if (card.isBingo()) {
          bingoedCard = card;
          break;
        }
      }
    } while (bingoedCard == null);
    System.out.println(bingoedCard.getScore());
  }
}
