package AoC14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC14/Data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part2(List<String> lines) {
        int n = lines.size();
        int m = lines.get(0).length();
        char[][] grid = new char[200][200];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        HashMap<Integer, HashMap<Integer, int[]>> tuples = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 'O') {
                    tuples.putIfAbsent(i, new HashMap<>());
                    tuples.get(i).put(j, new int[] {i, j});
                }
            }
        }

        for (int iter = 0; iter < 10000; ++iter) {
            // north IM LAZYYYYYYYYYYY
            for (int side = 0; side < 4; ++side) {
                for (int j = 0; j < m; ++j) {
                    int[] empty = new int[]{0, j};
                    for (int i = 0; i < n; ++i) {
                        char chr = grid[i][j];
                        if (chr == '#') {
                            empty = new int[]{i + 1, j};
                        } else if (chr == 'O') {
                            tuples.get(i).remove(j);
                            tuples.putIfAbsent(empty[0], new HashMap<>());
                            tuples.get(empty[0]).put(empty[1], empty);
                            empty = new int[]{i + 1, j};
                        }
                    }
                }
            }
        }

        System.out.println("hello");
    }

    public static void part1(List<String> lines) {
        int n = lines.size();
        int m = lines.get(0).length();
        char[][] grid = new char[200][200];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        int result = 0;
        for (int j = 0; j < m; ++j) {
            int currentWeight = 100;
            for (int i = 0; i < n; ++i) {
                char chr = grid[i][j];
                if (chr == '#') {
                    currentWeight = 100 - i - 1;
                    // continue;
                } else if (chr == 'O') {
                    result += currentWeight;
                    --currentWeight;
                }
            }
        }

        System.out.println(result);
    }



}
