package AoC07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.TreeSet;

class CardHand implements Comparable<CardHand> {

    public String label;
    public int bid;

    public CardHand(String label, int bid) {
        this.label = label;
        this.bid = bid;
    }

    @Override
    public int compareTo(CardHand o) {
        if (getType(this) > getType(o)) {
            return 1;
        }
        else if (getType( this) < getType(o)) {
            return -1;
        }
        for (int i = 0; i < 5; ++i) {
            if (this.label.charAt(i) != o.label.charAt(i)) {
                return precedence.indexOf(this.label.charAt(i)) > precedence.indexOf(o.label.charAt(i)) ? 1 : -1;
            }
        }
        return 0;
    }

    public static String precedence = "23456789TJQKA";

    public static int getType(CardHand cardHand) {
        // five of a kind
        if (cardHand.label.chars().distinct().count() == 1) {
            return 7;
        }
        // four of a kind or full house
        if (cardHand.label.chars().distinct().count() == 2) {
            char a = cardHand.label.chars().distinct().mapToObj(x -> (char) x).toList().get(0);
            int occurences = (int) cardHand.label.chars().filter(chr -> chr == a).count();
            if (occurences == 1 || occurences == 4) {
                return 6;
            } else {
                return 5;
            }
        }
        // three of a kind or two pairs
        if (cardHand.label.chars().distinct().count() == 3) {
            List<Character> list = cardHand.label.chars().distinct().mapToObj(x -> (char) x).toList();
            for (Character chr : list) {
                if (cardHand.label.chars().mapToObj(x -> (char) x).filter(x -> x == chr).count() == 3) {
                    return 4;
                }
            }
            return 3;
        }
        // one pair
        if (cardHand.label.chars().distinct().count() == 4) {
            return 2;
        }
        return 1;
    }

}

class CardHandJ implements Comparable<CardHandJ> {

    public String label;
    public int bid;

    public CardHandJ(String label, int bid) {
        this.label = label;
        this.bid = bid;
    }

    @Override
    public int compareTo(CardHandJ o) {
        if (getType(this) > getType(o)) {
            return 1;
        }
        else if (getType( this) < getType(o)) {
            return -1;
        }
        for (int i = 0; i < 5; ++i) {
            if (this.label.charAt(i) != o.label.charAt(i)) {
                return precedence.indexOf(this.label.charAt(i)) > precedence.indexOf(o.label.charAt(i)) ? 1 : -1;
            }
        }
        return 0;
    }

    public static String precedence = "J23456789TQKA";

    public static int getType(CardHandJ cardHand) {
        // five of a kind
        if (cardHand.label.chars().distinct().count() == 1
                || (cardHand.label.chars().distinct().count() == 2 && cardHand.label.contains("J"))) {
            return 7;
        }
        // four of a kind or full house
        if (cardHand.label.chars().distinct().count() == 2
                || (cardHand.label.chars().distinct().count() == 3 && cardHand.label.contains("J"))) {
            if (!cardHand.label.contains("J")) {
                char a = cardHand.label.chars().distinct().mapToObj(x -> (char) x).toList().get(0);
                int occurences = (int) cardHand.label.chars().filter(chr -> chr == a).count();
                if (occurences == 1 || occurences == 4) {
                    return 6;
                } else {
                    return 5;
                }
            }
            List<Character> list = cardHand.label.chars().distinct().mapToObj(x -> (char) x).toList();
            for (Character chr : list) {
                if (cardHand.label.chars().mapToObj(x -> (char) x).filter(x -> x == chr).count() == 3) {
                    return 6;
                }
            }
            if (cardHand.label.chars().mapToObj(x -> (char) x).filter(x -> x == 'J').count() >= 2) {
                return 6;
            }
            return 5;
        }
        // three of a kind or two pairs
        if (cardHand.label.chars().distinct().count() == 3
                || (cardHand.label.chars().distinct().count() == 4 && cardHand.label.contains("J"))) {
            if (!cardHand.label.contains("J")) {
                List<Character> list = cardHand.label.chars().distinct().mapToObj(x -> (char) x).toList();
                for (Character chr : list) {
                    if (cardHand.label.chars().mapToObj(x -> (char) x).filter(x -> x == chr).count() == 3) {
                        return 4;
                    }
                }
                return 3;
            }
            return 4;
        }
        // one pair
        if (cardHand.label.chars().distinct().count() == 4) {
            return 2;
        }
        if (cardHand.label.contains("J")) {
            return 2;
        }
        return 1;
    }

}

public class Day7 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/AoC07/data");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = br.lines().toList();

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        TreeSet<CardHand> hands = new TreeSet<>();

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            String label = parts[0];
            int bid = Integer.parseInt(parts[1]);
            hands.add(new CardHand(label, bid));
        }

        long result = 0;
        int rank = 1;
        for (CardHand hand : hands) {
            result += (long) rank * hand.bid;
            ++rank;
        }

        System.out.println(result);
    }

    public static void part2(List<String> lines) {
        TreeSet<CardHandJ> hands = new TreeSet<>();

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            String label = parts[0];
            int bid = Integer.parseInt(parts[1]);
            hands.add(new CardHandJ(label, bid));
        }

        long result = 0;
        int rank = 1;
        for (CardHandJ hand : hands) {
            result += (long) rank * hand.bid;
            ++rank;
        }

        System.out.println(result);
    }



}
