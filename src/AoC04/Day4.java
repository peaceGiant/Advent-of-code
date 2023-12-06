package AoC04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Day4 {

    public static int getPoints(String line) {
        String[] parts = line.split("\\|");
        String[] numbers = parts[0].split(":")[1].trim().split("\\s+");
        List<Integer> winNumbers = Arrays.stream(numbers).map(Integer::parseInt).toList();
        numbers = parts[1].trim().split("\\s+");
        List<Integer> myNumbers = Arrays.stream(numbers).map(Integer::parseInt).toList();

        return (int) Math.pow(2, myNumbers.stream().filter(winNumbers::contains).count() - 1);
    }

    public static int getScratchCards(String line) {
        String[] parts = line.split("\\|");
        String[] numbers = parts[0].split(":")[1].trim().split("\\s+");
        List<Integer> winNumbers = Arrays.stream(numbers).map(Integer::parseInt).toList();
        numbers = parts[1].trim().split("\\s+");
        List<Integer> myNumbers = Arrays.stream(numbers).map(Integer::parseInt).toList();

        return (int) myNumbers.stream().filter(winNumbers::contains).count();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\AoC04\\data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        int result = lines.stream().mapToInt(Day4::getPoints).sum();
        System.out.println(result);

        int numCards = lines.size();
        int[] weights = new int[numCards + 1];
        Arrays.fill(weights, 1);

        int sum = 0;
        for (int i = 0; i < numCards; ++i) {
            int scratchCards = getScratchCards(lines.get(i));
            for (int j = i + 1; j <= i + scratchCards && j < numCards; ++j) {
                weights[j] += weights[i];
            }
            sum += weights[i];
        }

        System.out.println(sum);

    }

}
