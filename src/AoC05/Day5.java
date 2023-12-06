package AoC05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


class Tuple {

    public Long source;
    public Long destination;
    public Long range;
    public Long offset;
    public Long limit;

    public Tuple(Long source, Long destination, Long range) {
        this.source = source;
        this.destination = destination;
        this.range = range;
        this.offset = destination - source;
        this.limit = source + range - 1;
    }

}

class Seed {

    public Long source;
    public Long range;
    public Long limit;

    public Seed(Long source, Long range) {
        this.source = source;
        this.range = range;
        this.limit = source + range - 1;
    }

}

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\AoC05\\data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        // parsing is data specific + breaking all the good design principles!
        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        List<Long> seeds = Arrays.stream(lines.get(0).substring(7).split("\\s+"))
                .map(Long::parseLong).toList();

        List<Tuple> seedToSoil = new ArrayList<>();
        // parsing is data specific
        for (int i = 3; i < 19; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            seedToSoil.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> soilToFert = new ArrayList<>();
        for (int i = 21; i < 39; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            soilToFert.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> fertToWater = new ArrayList<>();
        for (int i = 41; i < 81; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            fertToWater.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> waterToLight = new ArrayList<>();
        for (int i = 83; i < 99; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            waterToLight.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> lightToTemp = new ArrayList<>();
        for (int i = 101; i < 141; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            lightToTemp.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> tempToHumid = new ArrayList<>();
        for (int i = 143; i < 181; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            tempToHumid.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> humidToLoc = new ArrayList<>();
        for (int i = 183; i < 219; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            humidToLoc.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        Long result = Long.MAX_VALUE;
        for (Long seed : seeds) {
            Long path = seed;
            for (Tuple tuple : seedToSoil) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            for (Tuple tuple : soilToFert) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            for (Tuple tuple : fertToWater) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            for (Tuple tuple : waterToLight) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            for (Tuple tuple : lightToTemp) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            for (Tuple tuple : tempToHumid) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            for (Tuple tuple : humidToLoc) {
                if (path >= tuple.source && path <= tuple.limit) {
                    path = path + tuple.offset;
                    break;
                }
            }
            result = Math.min(result, path);
        }

        System.out.println(result);
    }

    public static void part2(List<String> lines) {
        List<Long> seedData = Arrays.stream(lines.get(0).substring(7).split("\\s+"))
                .map(Long::parseLong).toList();

        List<Seed> seeds = new ArrayList<>();
        for (int i = 0; i < seedData.size(); i += 2) {
            Long source = seedData.get(i);
            Long range = seedData.get(i + 1);
            seeds.add(new Seed(source, range));
        }

        List<Tuple> seedToSoil = new ArrayList<>();
        // parsing is data specific
        for (int i = 3; i < 19; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            seedToSoil.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> soilToFert = new ArrayList<>();
        for (int i = 21; i < 39; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            soilToFert.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> fertToWater = new ArrayList<>();
        for (int i = 41; i < 81; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            fertToWater.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> waterToLight = new ArrayList<>();
        for (int i = 83; i < 99; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            waterToLight.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> lightToTemp = new ArrayList<>();
        for (int i = 101; i < 141; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            lightToTemp.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> tempToHumid = new ArrayList<>();
        for (int i = 143; i < 181; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            tempToHumid.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        List<Tuple> humidToLoc = new ArrayList<>();
        for (int i = 183; i < 219; ++i) {
            String line = lines.get(i);
            List<Long> parts = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            humidToLoc.add(new Tuple(parts.get(1), parts.get(0), parts.get(2)));
        }

        Stack<Seed> stack = new Stack<>();
        for (Seed seed : seeds) {
            stack.push(seed);
        }

        List<Seed> resultSeeds = new ArrayList<>();
        Long result = Long.MAX_VALUE;
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : seedToSoil) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            stack.push(seed);
        }
        resultSeeds = new ArrayList<>();
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : soilToFert) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            stack.push(seed);
        }
        resultSeeds = new ArrayList<>();
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : fertToWater) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            stack.push(seed);
        }
        resultSeeds = new ArrayList<>();
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : waterToLight) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            stack.push(seed);
        }
        resultSeeds = new ArrayList<>();
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : lightToTemp) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            stack.push(seed);
        }
        resultSeeds = new ArrayList<>();
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : tempToHumid) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            stack.push(seed);
        }
        resultSeeds = new ArrayList<>();
        while (!stack.isEmpty()) {
            Seed curr = stack.pop();
            boolean flag = false;
            for (Tuple tuple : humidToLoc) {
                if (curr.limit < tuple.source || tuple.limit < curr.source) continue;
                flag = true;
                Long intervalStart = Math.max(curr.source, tuple.source);
                Long intervalEnd = Math.min(curr.limit, tuple.limit);
                Long intervalRange = intervalEnd - intervalStart + 1;
                resultSeeds.add(new Seed(intervalStart + tuple.offset, intervalRange));
                if (curr.source < intervalStart) {
                    stack.push(new Seed(curr.source, intervalStart - curr.source));
                }
                if (intervalEnd < curr.limit) {
                    stack.push(new Seed(intervalEnd + 1, curr.limit - intervalEnd));
                }
                break;
            }
            if (!flag) resultSeeds.add(curr);
        }
        seeds = resultSeeds;

        for (Seed seed : seeds) {
            result = Math.min(result, seed.source);
        }

        System.out.println(result);
    }

}
