package AoC01;

import java.io.*;
import java.util.HashMap;

public class Day1 {

    static int getCalibrationNumber(String line) {
        String firstDigit = "0", lastDigit = "0";
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("six", 6);
        hashMap.put("seven", 7);
        hashMap.put("eight", 8);
        hashMap.put("nine", 9);
        for (int i = 0; i < line.length(); ++i) {
            if (Character.isDigit(line.charAt(i))) {
                firstDigit = String.valueOf(line.charAt(i));
                break;
            }
            String temp = line.substring(i);
            for (String number : hashMap.keySet()) {
                if (temp.startsWith(number)) {
                    firstDigit = String.valueOf(hashMap.get(number));
                }
            }
            if (!firstDigit.equals("0")) break;
        }
        for (int i = line.length() - 1; i >= 0; --i) {
            if (Character.isDigit(line.charAt(i))) {
                lastDigit = (String.valueOf(line.charAt(i)));
                break;
            }
            String temp = line.substring(0, i + 1);
            for (String number : hashMap.keySet()) {
                if (temp.endsWith(number)) {
                    lastDigit = String.valueOf(hashMap.get(number));
                }
            }
            if (!lastDigit.equals("0")) break;
        }
        return Integer.parseInt(firstDigit + lastDigit);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\PeceG\\IdeaProjects\\adventOfCode\\adventOfCode\\src\\AoC01\\data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int result = br.lines().mapToInt(Day1::getCalibrationNumber).sum();
        System.out.println(result);

        br.close();
    }

}