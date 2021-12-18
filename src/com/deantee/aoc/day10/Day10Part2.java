/*
 * https://adventofcode.com/2021/day/10
 */

package com.deantee.aoc.day10;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.*;

public class Day10Part2 {

  private static final Map<Character, Character> brackets = new HashMap<>();
  private static final Map<Character, Integer> bracketScores = new HashMap<>();

  static {
    brackets.put('(', ')');
    brackets.put('[', ']');
    brackets.put('{', '}');
    brackets.put('<', '>');
    bracketScores.put(')', 1);
    bracketScores.put(']', 2);
    bracketScores.put('}', 3);
    bracketScores.put('>', 4);
  }

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(10);
    List<Long> scores = new ArrayList<>();
    for (String line : lines) {
      Deque<Character> characters = new ArrayDeque<>();
      boolean b = false;
      for (char c : line.toCharArray()) {
        if (brackets.containsKey(c)) characters.push(c);
        else if (brackets.get(characters.peek()) == c) characters.poll();
        else {
          b = true;
          break;
        }
      }
      if (b) continue;
      long score = 0;
      while (characters.peek() != null) {
        char c = characters.poll();
        score *= 5;
        score += bracketScores.get(brackets.get(c));
      }
      scores.add(score);
    }
    scores.sort(Comparator.comparingLong(o -> o));
    System.out.println(scores.get((scores.size() - 1) / 2));
  }
}
