package AoC21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class Tuple {

    int x;
    int y;
    int step;

    public Tuple(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != tuple.x) return false;
        if (y != tuple.y) return false;
        return step % 2 == tuple.step % 2;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (step % 2);
        return result;
    }

}

public class Day21 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC21/Data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
    }

    private static void part1(List<String> lines) {
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];
        Tuple source = null;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                grid[i][j] =  lines.get(i).charAt(j);
                if (grid[i][j] == 'S') {
                    source = new Tuple(i, j, 0);
                }
            }
        }

        HashSet<Tuple> seen = new HashSet<>();
        List<Tuple> valid = new ArrayList<>();
        Deque<Tuple> queue = new ArrayDeque<>();

        queue.add(source);
        while (!queue.isEmpty()) {
            Tuple current = queue.poll();
            if (seen.contains(current)) continue;
            seen.add(current);
            if (current.step % 2 == 0) valid.add(current);
            int x = current.x;
            int y = current.y;
            int step = current.step;
            if (x - 1 >= 0 && grid[x - 1][y] == '.')
                queue.add(new Tuple(x - 1, y, step + 1)); // north
            if (x + 1 < rows && (grid[x + 1][y] == '.'))
                queue.add(new Tuple(x + 1, y, step + 1)); // south
            if (y - 1 >= 0 && grid[x][y - 1] == '.')
                queue.add(new Tuple(x, y - 1, step + 1)); // west
            if (y + 1 < cols && grid[x][y + 1] == '.')
                queue.add(new Tuple(x, y + 1, step + 1)); // east
        }

        System.out.println(valid.stream().filter(tuple -> tuple.step <= 64).count());

    }

}
