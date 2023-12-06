package AoC06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class Tuple {

    public long time;
    public long distance;

    public Tuple(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

}

public class Day6 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\PeceG\\IdeaProjects\\adventOfCode\\adventOfCode\\src\\AoC06\\data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        String[] time = lines.get(0).substring(5).trim().split("\\s+");
        String[] dist = lines.get(1).substring(9).trim().split("\\s+");

        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            tuples.add(new Tuple(Integer.parseInt(time[i]), Integer.parseInt(dist[i])));
        }

        int result = 1;
        for (Tuple tuple : tuples) {
            int x1, x2;
            x1 = (int) Math.ceil((tuple.time - Math.sqrt(tuple.time * tuple.time - 4 * tuple.distance)) / 2);
            x2 = (int) Math.floor((tuple.time + Math.sqrt(tuple.time * tuple.time - 4 * tuple.distance)) / 2);
            if (Math.sqrt(tuple.time * tuple.time - 4 * tuple.distance) - (tuple.time * tuple.time - 4 * tuple.distance) == 0) {
                if (x1 == x2 || x1 == x2 - 1) {
                    result = 0;
                    break;
                }
                x1 += 1;
                x2 -= 1;
            }
            result *= Math.abs(x2 - x1) + 1;
        }

        System.out.println(result);
    }

    public static void part2(List<String> lines) {
        long time = Long.parseLong(lines.get(0).substring(5).trim().replace(" ", ""));
        long dist = Long.parseLong(lines.get(1).substring(9).trim().replace(" ", ""));

        Tuple tuple = new Tuple(time, dist);

        int result = 1;

        int x1, x2;
        x1 = (int) Math.ceil((tuple.time - Math.sqrt(tuple.time * tuple.time - 4 * tuple.distance)) / 2);
        x2 = (int) Math.floor((tuple.time + Math.sqrt(tuple.time * tuple.time - 4 * tuple.distance)) / 2);
        if (Math.sqrt(tuple.time * tuple.time - 4 * tuple.distance) - (tuple.time * tuple.time - 4 * tuple.distance) == 0) {
            if (x1 == x2 || x1 == x2 - 1) {
                result = 0;
                System.out.println(result);
                return;
            }
            x1 += 1;
            x2 -= 1;
        }
        result *= Math.abs(x2 - x1) + 1;

        System.out.println(result);
    }

}
