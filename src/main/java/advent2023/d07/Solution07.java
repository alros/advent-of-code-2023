package advent2023.d07;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution07 {
    public long step1(List<String> input) {
        return solve(input, false);
    }

    public long step2(List<String> input) {
        return solve(input, true);
    }

    private long solve(List<String> input, boolean activateJokers) {
        List<Hand> hands = input.stream()
                .map(s -> s.split(" "))
                .map(s -> new Hand(s[0], Integer.parseInt(s[1]), activateJokers))
                .sorted()
                .toList();
        long win = 0;
        for (int i = 0; i < hands.size(); i++) {
            win += (i + 1) * hands.get(i).getBid();
        }
        return win;
    }
}

@Getter
class Hand implements Comparable<Hand> {
    public static final int FIVE_OF_A_KIND = 7;
    public static final int FOUR_OF_A_KIND = 6;
    public static final int FULL_HOUSE = 5;
    public static final int THREE_OF_A_KIND = 4;
    public static final int TWO_PAIR = 3;
    public static final int ONE_PAIR = 2;
    public static final int HIGH_CARD = 1;

    private static final String[] orig = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    private static final String[] repl = {"b", "c", "d", "e", "f", "g", "h", "i", "l", "m", "n", "o", "p"};
    private static final String[] repJ = {"b", "c", "d", "e", "f", "g", "h", "i", "l", "a", "n", "o", "p"};

    private final char[] cards;
    private final int bid;

    public Hand(String cards, int bid) {
        this(cards, bid, false);
    }

    public Hand(String cards, int bid, boolean activateJokers) {
        for (int i = 0; i < orig.length; i++) {
            cards = cards.replaceAll(orig[i], activateJokers ? repJ[i] : repl[i]);
        }
        this.cards = cards.toCharArray();
        this.bid = bid;
    }

    public int type() {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : cards) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        if (map.size() == 1) {
            return FIVE_OF_A_KIND;
        }
        if (map.size() == 2) {
            if (containsJoker(map)) {
                return FIVE_OF_A_KIND;
            }
            if (map.get(cards[0]) == 4 || map.get(cards[1]) == 4) {
                return FOUR_OF_A_KIND;
            }
            return FULL_HOUSE;
        }
        if (map.size() == 3) {
            if (map.get(cards[0]) == 3 || map.get(cards[1]) == 3 || map.get(cards[2]) == 3) {
                if (getJokers(map) == 2) {
                    return FIVE_OF_A_KIND;
                }
                if (containsJoker(map)) {
                    return FOUR_OF_A_KIND;
                }
                return THREE_OF_A_KIND;
            } else {
                if(getJokers(map) == 1){
                    return FULL_HOUSE;
                }
                if(containsJoker(map)){
                    return FOUR_OF_A_KIND;
                }
                return TWO_PAIR;
            }
        }
        if (map.size() == 4) {
            return containsJoker(map) ? THREE_OF_A_KIND : ONE_PAIR;
        }
        if (map.size() == 5) {
            return containsJoker(map) ? ONE_PAIR : HIGH_CARD;
        }
        return -1;
    }

    private static Integer getJokers(Map<Character, Integer> map) {
        return map.getOrDefault('a', 0);
    }

    private static boolean containsJoker(Map<Character, Integer> map) {
        return map.containsKey('a');
    }

    @Override
    public int compareTo(Hand o) {
        if (type() == o.type()) {
            for (int i = 0; i < getCards().length; i++) {
                if (getCards()[i] != o.getCards()[i]) {
                    return getCards()[i] - o.getCards()[i];
                }
            }
            return 0;
        }
        return type() - o.type();
    }
}