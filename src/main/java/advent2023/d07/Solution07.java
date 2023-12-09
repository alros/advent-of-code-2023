package advent2023.d07;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution07 {
    public long step1(List<String> input) {
        List<Hand> hands = input.stream()
                .map(s -> s.split(" "))
                .map(s -> new Hand(s[0], Integer.parseInt(s[1])))
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

    private final char[] cards;
    private final int bid;

    public Hand(String cards, int bid) {
        this.cards = cards
                .replaceAll("2", "a")
                .replaceAll("3", "b")
                .replaceAll("4", "c")
                .replaceAll("5", "d")
                .replaceAll("6", "e")
                .replaceAll("7", "f")
                .replaceAll("8", "g")
                .replaceAll("9", "h")
                .replaceAll("T", "i")
                .replaceAll("J", "l")
                .replaceAll("Q", "m")
                .replaceAll("K", "n")
                .replaceAll("A", "o")
                .toCharArray();
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
            if (map.get(cards[0]) == 4 || map.get(cards[1]) == 4){
                return FOUR_OF_A_KIND;
            } else{
                return FULL_HOUSE;
            }
        }
        if (map.size() == 3) {
            if (map.get(cards[0]) == 3 || map.get(cards[1]) == 3 || map.get(cards[2]) == 3) {
                return THREE_OF_A_KIND;
            } else {
                return TWO_PAIR;
            }
        }
        if (map.size() == 5) {
            return HIGH_CARD;
        }
        if (map.size() == 4) {
            return ONE_PAIR;
        }
        return -1;
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