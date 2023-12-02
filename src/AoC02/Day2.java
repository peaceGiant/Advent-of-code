package AoC02;

import java.io.*;

public class Day2 {

    public static int redCubes = 12;
    public static int greenCubes = 13;
    public static int blueCubes = 14;

    public static int parseString(String line) {
        String info = line.split(":")[0];
        int id = Integer.parseInt(info.split(" ")[1]);

        String[] games = line.split(":")[1].split(";");

        for (String game : games) {
            String[] cubes = game.split(",");
            for (String cube : cubes) {
                switch (cube.trim().split(" ")[1].trim()) {
                    case "red":
                        if (Integer.parseInt(cube.trim().split(" ")[0]) > redCubes)
                            return 0;
                        break;
                    case "green":
                        if (Integer.parseInt(cube.trim().split(" ")[0]) > greenCubes)
                            return 0;
                        break;
                    case "blue":
                        if (Integer.parseInt(cube.trim().split(" ")[0]) > blueCubes)
                            return 0;
                        break;
                }
            }
        }

        return id;
    }

    public static int parseString2(String line) {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;

        String[] games = line.split(":")[1].split(";");

        for (String game : games) {
            String[] cubes = game.split(",");
            for (String cube : cubes) {
                switch (cube.trim().split(" ")[1].trim()) {
                    case "red":
                        if (Integer.parseInt(cube.trim().split(" ")[0]) > minRed)
                            minRed = Integer.parseInt(cube.trim().split(" ")[0]);
                        break;
                    case "green":
                        if (Integer.parseInt(cube.trim().split(" ")[0]) > minGreen)
                            minGreen = Integer.parseInt(cube.trim().split(" ")[0]);
                        break;
                    case "blue":
                        if (Integer.parseInt(cube.trim().split(" ")[0]) > minBlue)
                            minBlue = Integer.parseInt(cube.trim().split(" ")[0]);
                        break;
                }
            }
        }

        return minRed * minBlue * minGreen;
    }


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\PeceG\\IdeaProjects\\adventOfCode\\adventOfCode\\src\\AoC02\\data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int result = br.lines().mapToInt(Day2::parseString2).sum();
        System.out.println(result);

        System.out.println(parseString2("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"));

        br.close();
    }

}
