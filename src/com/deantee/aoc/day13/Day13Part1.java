/*
 * https://adventofcode.com/2021/day/13
 */

package com.deantee.aoc.day13;

import com.deantee.aoc.util.AOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13Part1 {

  public static void main(String[] args) throws IOException {
    String[] arguments = String.join("\n", AOC.get(13)).split("\n\n");
    List<Position> positionList = new ArrayList<>();
    for (String line : arguments[0].split("\n")) {
      String[] lineArgs = line.split(",");
      positionList.add(new Position(Integer.parseInt(lineArgs[0]), Integer.parseInt(lineArgs[1])));
    }
    String[] instructions = arguments[1].split("\n");
    String instruction = instructions[0];
    if (instruction.startsWith("fold along ")) {
      String[] instructionArgs = instruction.substring("fold along ".length()).split("=");
      int value = Integer.parseInt(instructionArgs[1]);
      if (instructionArgs[0].equals("x")) {
        for (Position position : new ArrayList<>(positionList)) {
          if (position.x() > value) {
            positionList.remove(position);
            Position newPosition = new Position(2 * value - position.x(), position.y());
            if (!positionList.contains(newPosition)) positionList.add(newPosition);
          }
        }
      } else if (instructionArgs[0].equals("y")) {
        for (Position position : new ArrayList<>(positionList)) {
          if (position.y() > value) {
            positionList.remove(position);
            Position newPosition = new Position(position.x(), 2 * value - position.y());
            if (!positionList.contains(newPosition)) positionList.add(newPosition);
          }
        }
      }
    }
    System.out.println(positionList.size());
  }
}
