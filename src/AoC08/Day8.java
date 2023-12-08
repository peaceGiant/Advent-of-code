package AoC08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Day8 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC08/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        String line = lines.get(0);
        List<Character> instructions = line.chars().mapToObj(x -> (char) x).toList();

        int n = lines.size();
        int m = instructions.size();

        Map<String, String[]> directions = new HashMap<>();
        for (int i = 2; i < n; ++i) {
            String[] parts = lines.get(i).split("=");
            String key = parts[0].trim();
            String[] value = Arrays.stream(parts[1].trim().split("\\s+"))
                    .map(x -> x.trim().replaceAll("[^a-zA-Z]", ""))
                    .toArray(String[]::new);
            directions.put(key, value);
        }

        long counter = 0;
        String key = "AAA";
        while (!key.equals("ZZZ")) {
            char next = instructions.get((int) (counter % m));
            if (next == 'L') {
                key = directions.get(key)[0];
            } else {
                key = directions.get(key)[1];
            }
            ++counter;
        }

        System.out.println(counter);
    }

    public static void part2(List<String> lines) {
        String line = lines.get(0);
        List<Character> instructions = line.chars().mapToObj(x -> (char) x).toList();

        int n = lines.size();
        int m = instructions.size();

        Map<String, String[]> directions = new HashMap<>();
        for (int i = 2; i < n; ++i) {
            String[] parts = lines.get(i).split("=");
            String key = parts[0].trim();
            String[] value = Arrays.stream(parts[1].trim().split("\\s+"))
                    .map(x -> x.trim().replaceAll("[^a-zA-Z]", ""))
                    .toArray(String[]::new);
            directions.put(key, value);
        }

        List<String> sourceNodes = new ArrayList<>();
        for (String key: directions.keySet()) {
            if (key.endsWith("A")) {
                sourceNodes.add(key);
            }
        }

        List<Long> counters = new ArrayList<>();
        for (String key : sourceNodes) {
            long counter = 0;
            String currrKey = key;
            while (!currrKey.endsWith("Z")) {
                char next = instructions.get((int) (counter % m));
                if (next == 'L') {
                    currrKey = directions.get(currrKey)[0];
                } else {
                    currrKey = directions.get(currrKey)[1];
                }
                ++counter;
            }
            counters.add(counter);
        }

        long result = 1;
        for (long counter : counters) {
            result = lcm(result, counter);
        }
        System.out.println(result);
    }

    // copied
    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

}
