/*
 * https://adventofcode.com/2021/day/4
 */

package com.deantee.aoc.day04;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04Part2 {

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(4);
    List<Integer> numbers = new ArrayList<>(Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList());
    List<BingoCard> cards = new ArrayList<>();
    for (int i = 0; i <= (lines.size() - 4 - 3) / 6; i++) {
      List<String> bingoContent = lines.subList(i * 6 + 2, i * 6 + 2 + 5);
      cards.add(new BingoCard(bingoContent));
    }
    int number;
    do {
      number = numbers.remove(0);
      for (BingoCard card : new ArrayList<>(cards)) {
        card.mark(number);
        if (card.isBingo()) {
          cards.remove(card);
        }
      }
    } while (cards.size() > 1);
    BingoCard lastCard = cards.get(0);
    do {
      number = numbers.remove(0);
      lastCard.mark(number);
    } while (!lastCard.isBingo());
    System.out.println(lastCard.getScore());
  }
}
