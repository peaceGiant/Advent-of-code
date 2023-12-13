package AoC13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC13/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part2(List<String> lines) {
        List<List<String>> blocks = parseBlocks1(lines);
        long result = 0;
        for (List<String> block: blocks) {
            Optional<Integer> column = findReflectionColumnWithSmudge(block);
            Optional<Integer> row = findReflectionRowsWithSmudge(block);
            if (column.isPresent()) {
                result += column.get();
            }
            if (row.isPresent()) {
                result += 100L * row.get();
            }
        }

        System.out.println(result);
    }

    public static boolean areSmudges(String line1, String line2) {
        int differences = 0;
        for (int i = 0; i < line1.length(); ++i) {
            if (line1.charAt(i) != line2.charAt(i)) {
                ++differences;
            }
        }
        return differences == 1;
    }

    public static Optional<Integer> findReflectionRowsWithSmudge(List<String> block) {
        int m = block.size();

        for (int i = 0; i < m - 1; ++i) {
            int left = i;
            int right = i + 1;
            boolean isValid = true;
            int differences = 1;
            while (0 <= left && right < m) {
                if (!block.get(left).equals(block.get(right)) && !areSmudges(block.get(left), block.get(right))) {
                    isValid = false;
                    break;
                }
                if (areSmudges(block.get(left), block.get(right))) {
                    --differences;
                }
                if (differences < 0) {
                    isValid = false;
                    break;
                }
                --left; ++right;
            }
            if (isValid && differences == 0) {
                return Optional.of(i + 1);
            }
        }
        return Optional.empty();
    }

    public static Optional<Integer> findReflectionColumnWithSmudge(List<String> block) {
        List<String> columns = new ArrayList<>();
        int m = block.get(0).length();
        for (int i = 0; i < m; ++i) {
            StringBuilder column = new StringBuilder();
            for (String s : block) {
                column.append(s.charAt(i));
            }
            columns.add(column.toString());
        }

        for (int i = 0; i < m - 1; ++i) {
            int left = i;
            int right = i + 1;
            boolean isValid = true;
            int differences = 1;
            while (0 <= left && right < m) {
                if (!columns.get(left).equals(columns.get(right)) && !areSmudges(columns.get(left), columns.get(right))) {
                    isValid = false;
                    break;
                }
                if (areSmudges(columns.get(left), columns.get(right))) {
                    --differences;
                }
                if (differences < 0) {
                    isValid = false;
                    break;
                }
                --left; ++right;
            }
            if (isValid && differences == 0) {
                return Optional.of(i + 1);
            }
        }
        return Optional.empty();
    }

    public static void part1(List<String> lines) {
        List<List<String>> blocks = parseBlocks1(lines);
        long result = 0;
        for (List<String> block: blocks) {
            Optional<List<Integer>> columns = findReflectionColumn(block);
            Optional<List<Integer>> rows = findReflectionRows(block);
            if (columns.isPresent()) {
                for (int column : columns.get())
                    result += column;
            }
            if (rows.isPresent()) {
                for (int row : rows.get())
                    result += 100L * row;
            }
        }

        System.out.println(result);
    }

    public static Optional<List<Integer>> findReflectionRows(List<String> block) {
        int m = block.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < m - 1; ++i) {
            int left = i;
            int right = i + 1;
            boolean isValid = true;
            while (0 <= left && right < m) {
                if (!block.get(left).equals(block.get(right))) {
                    isValid = false;
                    break;
                }
                --left; ++right;
            }
            if (isValid) {
                results.add(i + 1);
            }
        }
        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }

    public static Optional<List<Integer>> findReflectionColumn(List<String> block) {
        List<String> columns = new ArrayList<>();
        int m = block.get(0).length();
        for (int i = 0; i < m; ++i) {
            StringBuilder column = new StringBuilder();
            for (String s : block) {
                column.append(s.charAt(i));
            }
            columns.add(column.toString());
        }
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < m - 1; ++i) {
            int left = i;
            int right = i + 1;
            boolean isValid = true;
            while (0 <= left && right < m) {
                if (!columns.get(left).equals(columns.get(right))) {
                    isValid = false;
                    break;
                }
                --left; ++right;
            }
            if (isValid) {
                results.add(i + 1);
            }
        }
        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }

    public static List<List<String>> parseBlocks1(List<String> lines) {
        List<List<String>> result = new ArrayList<>();
        Iterator<String> iterator = lines.iterator();
        List<String> block = new ArrayList<>();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.isEmpty()) {
                result.add(block);
                block = new ArrayList<>();
                continue;
            }
            block.add(line);
        }
        result.add(block);
        return result;
    }

}
