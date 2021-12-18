/*
 * https://adventofcode.com/2021/day/10
 */

package com.deantee.aoc.day10;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.*;

public class Day10Part1 {

  private static final Map<Character, Character> brackets = new HashMap<>();
  private static final Map<Character, Integer> bracketScores = new HashMap<>();

  static {
    brackets.put('(', ')');
    brackets.put('[', ']');
    brackets.put('{', '}');
    brackets.put('<', '>');
    bracketScores.put(')', 3);
    bracketScores.put(']', 57);
    bracketScores.put('}', 1197);
    bracketScores.put('>', 25137);
  }

  public static void main(String[] args) throws IOException {
    List<String> lines = AOC.get(10);
    int score = 0;
    for (String line : lines) {
      Deque<Character> characters = new ArrayDeque<>();
      for (char c : line.toCharArray()) {
        if (brackets.containsKey(c)) characters.push(c);
        else if (brackets.get(characters.peek()) == c) characters.poll();
        else {
          score += bracketScores.get(c);
          break;
        }
      }
    }
    System.out.println(score);
  }
}
