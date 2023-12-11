package AoC11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day11 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC11/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1_2(lines);
    }

    public static void part1_2(List<String> lines) {
        List<Integer> rowsToExpand = IntStream.range(0, lines.size())
                .filter(i -> lines.get(i).chars().distinct().count() == 1).boxed().toList();
        List<Integer> colsToExpand = new ArrayList<>();
        for (int j = 0; j < lines.get(0).length(); ++j) {
            boolean isEmpty = true;
            for (String line : lines) {
                if (line.charAt(j) == '#') {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                colsToExpand.add(j);
            }
        }

        List<int[]> galaxies = new ArrayList<>();
        for (int i = 0; i < lines.size(); ++i) {
            for (int j = 0; j < lines.get(0).length(); ++j) {
                if (lines.get(i).charAt(j) == '#') {
                    galaxies.add(new int[] {i, j});
                }
            }
        }

        long result = 0;
        for (int[] galaxy1 : galaxies) {
            int x1 = galaxy1[0], y1 = galaxy1[1];
            for (int[] galaxy2 : galaxies) {
                int x2 = galaxy2[0], y2 = galaxy2[1];
                long dist = Math.abs(x2 - x1) + Math.abs(y2 - y1);
                for (int rows : rowsToExpand) {
                    if (rows > Math.min(x1, x2) && rows < Math.max(x1, x2)) {
                        dist += 1000000 - 1;  // for part 1, just ++dist;
                    }
                }
                for (int cols : colsToExpand) {
                    if (cols > Math.min(y1, y2) && cols < Math.max(y1, y2)) {
                        dist += 1000000 - 1;
                    }
                }
                result += dist;
            }
        }

        System.out.println(result / 2);
    }

}
