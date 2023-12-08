package AoC08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Day8Upgraded {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC08/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part2(lines);
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

        int numZ = (int) directions.keySet().stream().filter(key -> key.endsWith("Z")).count();

        List<List<Long>> counters = new ArrayList<>();
        for (String key : sourceNodes) {
            List<Long> keyCounters = new ArrayList<>();
            long counter = 0;
            String currrKey = key;
            while (keyCounters.size() < numZ) {
                char next = instructions.get((int) (counter % m));
                if (next == 'L') {
                    currrKey = directions.get(currrKey)[0];
                } else {
                    currrKey = directions.get(currrKey)[1];
                }
                ++counter;
                if (currrKey.endsWith("Z")) {
                    keyCounters.add(counter);
                }
            }
            counters.add(keyCounters);
        }

        List<List<Long>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        for (List<Long> list : counters) {
            List<List<Long>> tempResults = new ArrayList<>();
            for (List<Long> result : results) {
                for (Long number : list) {
                    List<Long> temp = new ArrayList<>(result);
                    temp.add(number);
                    tempResults.add(temp);
                }
            }
            results = tempResults;
        }

        long finalRes = Long.MAX_VALUE;
        for (List<Long> result : results) {
            long temp = result.stream().reduce(Day8Upgraded::lcm).orElse(0L);
            finalRes = Math.min(finalRes, temp);
        }
        System.out.println(finalRes);
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

