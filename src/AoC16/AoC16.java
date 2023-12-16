package AoC16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class Light {

    int x;
    int y;
    String direction;

    public Light(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Light light = (Light) o;

        if (x != light.x) return false;
        if (y != light.y) return false;
        return direction.equals(light.direction);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + direction.hashCode();
        return result;
    }
}

public class AoC16 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC16/Data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines, new Light(0, 0, "R"));
        part2(lines);
    }

    private static void part2(List<String> lines) {
        int n = lines.size();
        int maxEnergy = 0;
        for (int i = 0; i < n; ++i) {
            maxEnergy = Math.max(maxEnergy, part1(lines, new Light(0, i, "D")));
            maxEnergy = Math.max(maxEnergy, part1(lines, new Light(n - 1, i, "U")));
            maxEnergy = Math.max(maxEnergy, part1(lines, new Light(i, 0, "R")));
            maxEnergy = Math.max(maxEnergy, part1(lines, new Light(i, n - 1, "L")));
        }
        System.out.println("Maximum energy: " + maxEnergy);
    }

    static Queue<Light> queue = new ArrayDeque<>();

    private static int part1(List<String> lines, Light source) {
        int m = lines.size();
        int n = lines.get(0).length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        boolean[][] energized = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(energized[i], false);
        }

        queue.add(source);
        HashSet<Light> seen = new HashSet<>();

        while (!queue.isEmpty()) {
            Light light = queue.poll();
            if (seen.contains(light)) continue;
            int i = light.x;
            int j = light.y;
            if (i < 0 || i >= m || j < 0 || j >= n) continue;
            seen.add(light);
            switch (light.direction) {
                case "U" -> {
                    energized[i][j] = true;
                    if (grid[i][j] == '.' || grid[i][j] == '|') {
                        queue.add(new Light(i - 1, j, "U"));
                    } else if (grid[i][j] == '\\') {
                        queue.add(new Light(i, j - 1, "L"));
                    } else if (grid[i][j] == '/') {
                        queue.add(new Light(i, j + 1, "R"));
                    } else { // split
                        queue.add(new Light(i, j - 1, "L"));
                        queue.add(new Light(i, j + 1, "R"));
                    }
                }
                case "R" -> {
                    energized[i][j] = true;
                    if (grid[i][j] == '.' || grid[i][j] == '-') {
                        queue.add(new Light(i, j + 1, "R"));
                    } else if (grid[i][j] == '\\') {
                        queue.add(new Light(i + 1, j, "D"));
                    } else if (grid[i][j] == '/') {
                        queue.add(new Light(i - 1, j, "U"));
                    } else { // split
                        queue.add(new Light(i - 1, j, "U"));
                        queue.add(new Light(i + 1, j, "D"));
                    }
                }
                case "D" -> {
                    energized[i][j] = true;
                    if (grid[i][j] == '.' || grid[i][j] == '|') {
                        queue.add(new Light(i + 1, j, "D"));
                    } else if (grid[i][j] == '\\') {
                        queue.add(new Light(i, j + 1, "R"));
                    } else if (grid[i][j] == '/') {
                        queue.add(new Light(i, j - 1, "L"));
                    } else { // split
                        queue.add(new Light(i, j - 1, "L"));
                        queue.add(new Light(i, j + 1, "R"));
                    }
                }
                case "L" -> {
                    energized[i][j] = true;
                    if (grid[i][j] == '.' || grid[i][j] == '-') {
                        queue.add(new Light(i, j - 1, "L"));
                    } else if (grid[i][j] == '\\') {
                        queue.add(new Light(i - 1, j, "U"));
                    } else if (grid[i][j] == '/') {
                        queue.add(new Light(i + 1, j, "D"));
                    } else { // split
                        queue.add(new Light(i - 1, j, "U"));
                        queue.add(new Light(i + 1, j, "D"));
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                result += energized[i][j] ? 1 : 0;
            }
        }

        System.out.println(result);
        return result;
    }

}
