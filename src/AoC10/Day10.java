package AoC10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day10 {

    public static String LEFT = "LEFT";
    public static String RIGHT = "RIGHT";
    public static String UP = "UP";
    public static String DOWN = "DOWN";

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC10/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        int[] coordinatesStart = findStart(lines);
        int[] nextPipe = findOnePipe(lines, coordinatesStart);
        String sourceDirection = LEFT;
        int loopLength = 2;  // source + first neighbor
        while (nextPipe[0] != coordinatesStart[0] || nextPipe[1] != coordinatesStart[1]) {
            int i = nextPipe[0], j = nextPipe[1];
            char currentPipeSymbol = lines.get(i).charAt(j);
            switch (currentPipeSymbol) {
                case '|':
                    nextPipe = sourceDirection.equals(UP) ? new int[] {i + 1, j} : new int[] {i - 1, j};
                    break;
                case '-':
                    nextPipe = sourceDirection.equals(LEFT) ? new int[] {i, j + 1} : new int[] {i, j - 1};
                    break;
                case 'J':
                    nextPipe = sourceDirection.equals(LEFT) ? new int[] {i - 1, j} : new int[] {i, j - 1};
                    sourceDirection = sourceDirection.equals(LEFT) ? DOWN : RIGHT;
                    break;
                case '7':
                    nextPipe = sourceDirection.equals(LEFT) ? new int[] {i + 1, j} : new int[] {i, j - 1};
                    sourceDirection = sourceDirection.equals(LEFT) ? UP : RIGHT;
                    break;
                case 'F':
                    nextPipe = sourceDirection.equals(RIGHT) ? new int[] {i + 1, j} : new int[] {i, j + 1};
                    sourceDirection = sourceDirection.equals(RIGHT) ? UP : LEFT;
                    break;
                case 'L':
                    nextPipe = sourceDirection.equals(RIGHT) ? new int[] {i - 1, j} : new int[] {i, j + 1};
                    sourceDirection = sourceDirection.equals(RIGHT) ? DOWN : LEFT;
                    break;
            }
            loopLength += 1;
        }

        System.out.println(loopLength / 2);
    }

    public static void part2(List<String> lines) {
        int[] coordinatesStart = findStart(lines);
        int[] nextPipe = findOnePipe(lines, coordinatesStart);
        String sourceDirection = LEFT;
        List<int[]> path = new ArrayList<>();
        path.add(coordinatesStart);
        path.add(nextPipe);
        while (nextPipe[0] != coordinatesStart[0] || nextPipe[1] != coordinatesStart[1]) {
            path.add(nextPipe);
            int i = nextPipe[0], j = nextPipe[1];
            char currentPipeSymbol = lines.get(i).charAt(j);
            switch (currentPipeSymbol) {
                case '|':
                    nextPipe = sourceDirection.equals(UP) ? new int[] {i + 1, j} : new int[] {i - 1, j};
                    break;
                case '-':
                    nextPipe = sourceDirection.equals(LEFT) ? new int[] {i, j + 1} : new int[] {i, j - 1};
                    break;
                case 'J':
                    nextPipe = sourceDirection.equals(LEFT) ? new int[] {i - 1, j} : new int[] {i, j - 1};
                    sourceDirection = sourceDirection.equals(LEFT) ? DOWN : RIGHT;
                    break;
                case '7':
                    nextPipe = sourceDirection.equals(LEFT) ? new int[] {i + 1, j} : new int[] {i, j - 1};
                    sourceDirection = sourceDirection.equals(LEFT) ? UP : RIGHT;
                    break;
                case 'F':
                    nextPipe = sourceDirection.equals(RIGHT) ? new int[] {i + 1, j} : new int[] {i, j + 1};
                    sourceDirection = sourceDirection.equals(RIGHT) ? UP : LEFT;
                    break;
                case 'L':
                    nextPipe = sourceDirection.equals(RIGHT) ? new int[] {i - 1, j} : new int[] {i, j + 1};
                    sourceDirection = sourceDirection.equals(RIGHT) ? DOWN : LEFT;
                    break;
            }
        }

        HashMap<Integer, HashMap<Integer, Boolean>> hasPathMap = new HashMap<>();
        for (int[] coord : path) {
            hasPathMap.putIfAbsent(coord[0], new HashMap<>());
            hasPathMap.get(coord[0]).put(coord[1], true);
        }

        int result = 0;
        int m = lines.size();
        int n = lines.get(0).length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (hasPathMap.get(i) == null || hasPathMap.get(i).get(j) == null) {
                    int counter;
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < j; ++k) {
                        if (hasPathMap.get(i) != null && hasPathMap.get(i).get(k) != null) {
                            char chr = lines.get(i).charAt(k);
                            if (chr == '|' || chr == 'F' || chr == 'L' || chr == '7' || chr == 'J' || chr == 'S') {
                                sb.append(chr);
                            }
                        }
                    }
                    counter = sb.toString().replaceAll("F7", "")
                            .replaceAll("FJ", "|")
                            .replaceAll("L7", "|")
                            .replaceAll("LJ", "")
                            .replaceAll("S7", "|")  // enough for given input
                            .length();
                    int counter1;
                    StringBuilder sb1 = new StringBuilder();
                    for (int k = 0; k < i; ++k) {
                        if (hasPathMap.get(k) != null && hasPathMap.get(k).get(j) != null) {
                            char chr = lines.get(k).charAt(j);
                            if (chr == '-' || chr == '7' || chr == 'F' || chr == 'L' || chr == 'J' || chr == 'S') {
                                sb1.append(chr);
                            }
                        }
                    }
                    counter1 = sb1.toString().replaceAll("7J", "")
                            .replaceAll("FL", "")
                            .replaceAll("7L", "-")
                            .replaceAll("FJ", "-")
                            .replaceAll("7S", "-")  // input specific line
                            .length();
                    result = counter % 2 == 0 || counter1 % 2 == 0 ? result : result + 1;
                }
            }
        }

        System.out.println(result);
    }

    public static int[] findOnePipe(List<String> lines, int[] coordinateStart) {
        int i = coordinateStart[0];
        int j = coordinateStart[1];
        if (lines.get(i).charAt(j + 1) == '7' || lines.get(i).charAt(j + 1) == 'J' || lines.get(i).charAt(j + 1) == '-') {
            return new int[] {i, j + 1};
        }
        return new int[] {0, 0};  // Won't happen for given input
    }

    public static int[] findStart(List<String> lines) {
        int m = lines.size();
        int n = lines.get(0).length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (lines.get(i).charAt(j) == 'S') {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};  // Won't happen for given input
    }

}
