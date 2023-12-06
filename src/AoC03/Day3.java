package AoC03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Tuple {

    public int beginIndex;
    public int endIndex;
    public int row;
    public int number;

    public Tuple(int beginIndex, int endIndex, int row, int number) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.row = row;
        this.number = number;
    }

}

class Gear {

    public int x, y;
    public int num_1, num_2;
    public int counter;

    public Gear(int x, int y) {
        this.x = x;
        this.y = y;
        counter = 0;
        num_2 = -1;
        num_1 = -1;
    }

}

public class Day3 {

    public static void main(String[] args) throws IOException {
        File file = new File("src\\AoC03\\data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        int rows = lines.size();
        int cols = lines.get(0).length();

        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = lines.get(i).charAt(j);
            }
        }

        // PART 1 - probably the worst code I've ever written
        List<Tuple> numbers = new ArrayList<>();
        int beginIndex, endIndex, row;
        List<Character> digits = new ArrayList<>();
        for (int i = 0; i < rows; ++i) {
            row = i;
            beginIndex = -1;
            for (int j = 0; j < cols; ++j) {
                char curr = matrix[i][j];
                if (Character.isDigit(curr)) {
                    digits.add(curr);
                    if (beginIndex == -1) {
                        beginIndex = j;
                    }
                } else {
                    if (!digits.isEmpty()) {
                        int number = Integer.parseInt(digits.stream().map(String::valueOf).reduce((x, y) -> x+y).get());
                        endIndex = j - 1;
                        numbers.add(new Tuple(beginIndex, endIndex, row, number));
                        digits.clear();
                    }
                    beginIndex = -1;
                }
            }
            if (!digits.isEmpty()) {
                int number = Integer.parseInt(digits.stream().map(String::valueOf).reduce((x, y) -> x+y).get());
                endIndex = rows - 1;
                numbers.add(new Tuple(beginIndex, endIndex, row, number));
                digits.clear();
            }
        }

        int sum = 0;
        for (Tuple number : numbers) {
            boolean hasSymbol = false;
            for (int i = number.beginIndex - 1; i <= number.endIndex + 1; ++i) {
                for (int j = number.row - 1; j <= number.row + 1; ++j) {
                    if (0 <= i && 0 <= j && i < cols && j < rows) {
                        char symbol = matrix[j][i];
                        if (!Character.isDigit(symbol) && symbol != '.') {
                            hasSymbol = true;
                            i = number.endIndex + 1;
                            break;
                        }
                    }
                }
            }
            if (hasSymbol) {
                sum += number.number;
            }
        }

        System.out.println(sum);

        // PART 2
        List<Gear> gears = new ArrayList<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == '*') {
                    gears.add(new Gear(i, j));
                }
            }
        }

        for (Tuple number : numbers) {
            for (int i = number.beginIndex - 1; i <= number.endIndex + 1; ++i) {
                for (int j = number.row - 1; j <= number.row + 1; ++j) {
                    if (0 <= i && 0 <= j && i < cols && j < rows) {
                        char symbol = matrix[j][i];
                        if (symbol == '*') {
                            int x1 = j;
                            int y1 = i;

                            Optional<Gear> foundYou = gears.stream().filter(gear -> gear.x == x1 && gear.y == y1).findFirst();
                            if (foundYou.isPresent()) {
                                foundYou.get().counter++;
                                if (foundYou.get().num_1 != -1) foundYou.get().num_2 = number.number;
                                else foundYou.get().num_1 = number.number;
                            }
                        }
                    }
                }
            }
        }

        int result = gears.stream().filter(gear -> gear.counter == 2).mapToInt(gear -> gear.num_1 * gear.num_2).sum();

        System.out.println(result);

    }

}
