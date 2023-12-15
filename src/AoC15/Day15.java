package AoC15;

import java.io.*;
import java.util.*;

public class Day15 {

    public static void main(String[] args) throws IOException {
        File file = new File("src/AoC15/Data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();

        part1(line);
        part2(line);
    }

    private static void part2(String line) {
        List<String> words = Arrays.stream(line.split(",")).toList();
        HashMap<Integer, LinkedHashMap<String, Integer>> hashMap = new HashMap<>();

        for (int i = 0; i < 256; ++i) {
            hashMap.put(i, new LinkedHashMap<>());
        }

        for (String word: words) {
            if (word.endsWith("-")) {
                String label = word.replace("-", "");
                long hash = hash(label);
                LinkedHashMap<String, Integer> boxes = hashMap.get((int) hash);
                if (boxes.get(label) != null) {
                    boxes.remove(label);
                }
            } else {
                String label = word.replaceFirst("=\\d$", "");
                long hash = hash(label);
                LinkedHashMap<String, Integer> boxes = hashMap.get((int) hash);
                if (boxes.get(label) != null) {
                    boxes.replace(label, (int) word.charAt(word.length() - 1) - '0');
                } else {
                    boxes.put(label, (int) word.charAt(word.length() - 1) - '0');
                }
            }
        }

        long result = 0;
        for (int i = 0; i < 256; ++i) {
            LinkedHashMap<String, Integer> box = hashMap.get(i);
            int n = box.size();
            Iterator<Integer> focals = box.values().iterator();
            for (int j = 0; j < n; ++j) {
                result += (long) (i + 1) * (j + 1) * focals.next();
            }
        }

        System.out.println(result);
    }

    private static long hash(String word) {
        long hash = 0;
        for (char chr: word.chars().mapToObj(x -> (char) x).toList()) {
            hash += (int) chr;
            hash *= 17;
            hash %= 256;
        }
        return hash;
    }

    private static void part1(String line) {
        List<String> words = Arrays.stream(line.split(",")).toList();
        long result = 0;
        for (String word: words) {
            long hash = 0;
            for (char chr: word.chars().mapToObj(x -> (char) x).toList()) {
                hash += (int) chr;
                hash *= 17;
                hash %= 256;
            }
            result += hash;
        }
        System.out.println(result);
    }


}
