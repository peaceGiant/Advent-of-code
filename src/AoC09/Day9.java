package AoC09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC09/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        long result = 0;
        for (String line : lines) {
            List<Integer> numbers = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            List<List<Integer>> iterations = new ArrayList<>();
            iterations.add(numbers);
            while (iterations.get(iterations.size() - 1).stream().distinct().count() > 1) {
                List<Integer> iteration = new ArrayList<>();
                List<Integer> previousIteration = iterations.get(iterations.size() - 1);
                int n = previousIteration.size();
                for (int i = 1; i < n; ++i) {
                    iteration.add(previousIteration.get(i) - previousIteration.get(i - 1));
                }
                iterations.add(iteration);
            }
            for (List<Integer> iteration : iterations) {
                result += (long) iteration.get(iteration.size() - 1);
            }
        }

        System.out.println(result);
    }

    public static void part2(List<String> lines) {
        long result = 0;
        for (String line : lines) {
            List<Integer> numbers = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            List<List<Integer>> iterations = new ArrayList<>();
            iterations.add(numbers);
            while (iterations.get(iterations.size() - 1).stream().distinct().count() > 1) {
                List<Integer> iteration = new ArrayList<>();
                List<Integer> previousIteration = iterations.get(iterations.size() - 1);
                int n = previousIteration.size();
                for (int i = 1; i < n; ++i) {
                    iteration.add(previousIteration.get(i) - previousIteration.get(i - 1));
                }
                iterations.add(iteration);
            }
            int n = iterations.size();
            for (int i = 0; i < n; ++i) {
                int parity = i % 2 == 0 ? 1 : -1;
                result += (long) iterations.get(i).get(0) * parity;
            }
        }

        System.out.println(result);
    }

}
