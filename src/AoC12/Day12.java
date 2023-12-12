package AoC12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC12/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        long result = 0;
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            String row = parts[0];
            List<Integer> operational = Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList();

            List<String> arrangements = new ArrayList<>();
            arrangements.add("");
            for (int i = 0; i < row.length(); ++i) {
                List<String> currArrangements = new ArrayList<>();
                for (String arrangement: arrangements) {
                    if (".#".contains(String.valueOf(row.charAt(i)))) {
                        currArrangements.add(arrangement + row.charAt(i));
                    } else {
                        currArrangements.add(arrangement + "#");
                        currArrangements.add(arrangement + ".");
                    }
                }
                arrangements = new ArrayList<>(currArrangements);
            }

            outer_loop:
            for (String arrangement: arrangements) {
                List<Integer> arrangementOperational = Arrays.stream(arrangement.replaceAll("^\\.+", "")
                        .replaceAll("\\.+$", "").trim().split("\\.+")).map(String::length).toList();
                if (arrangementOperational.size() != operational.size()) continue;
                for (int i = 0; i < operational.size(); ++i) {
                    if (arrangementOperational.get(i).intValue() != operational.get(i)) continue outer_loop;
                }
                ++result;
            }
        }
        System.out.println(result);
    }

    public static void part2(List<String> lines) {
        long result = 0;
        for (String line: lines) {
            String[] parts = line.split("\\s+");
            String row = (parts[0] + "?").repeat(5).substring(0, parts[0].length() * 5 + 4);
            String operationalString = (parts[1] + ",").repeat(5).substring(0, parts[1].length() * 5 + 4);
            List<Integer> operational = Arrays.stream(operationalString.split(",")).map(Integer::parseInt).toList();
            result += recursion(row, operational);
        }
        System.out.println(result);
    }

    // incorrect recursion + memoization is needed
    private static long recursion(String row, List<Integer> operational) {
        int length = operational.get(0);
        List<Integer> newOperational = new ArrayList<>(operational);
        newOperational.remove(0);

        if (newOperational.isEmpty()) return 1;
        if (row.length() < operational.size()) return 0;

        long result = 0;
        int n = row.length();
        for (int i = 0; i < n; ++i) {
            if (i + length < n && row.charAt(i + length) == '#' && row.charAt(i) == '#') break;
            if (i + length < n && row.substring(i, i + length).contains(".")) continue;
            if (i + length >= n) break;
            result += recursion(row.substring(i + length + 1), newOperational);
        }

        return result;
    }

}
