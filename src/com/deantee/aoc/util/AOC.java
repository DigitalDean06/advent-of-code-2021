/*
 * https://adventofcode.com/
 */

package com.deantee.aoc.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC {

  public static List<String> get(int day) throws IOException {
    return Files.readAllLines(Paths.get("inputs", String.format("day-%d.txt", day)));
  }
}
