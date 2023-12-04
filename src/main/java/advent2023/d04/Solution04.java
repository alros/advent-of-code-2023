package advent2023.d04;

import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;


public class Solution04 {

    private static final Pattern pattern = Pattern.compile("Card +(\\d+): +([\\d ]+)\\| +([\\d ]+)");

    public int step1(List<String> input) {
        return input.stream()
                .map(l -> pattern.matcher(l))
                .filter(Matcher::matches)
                .map(Card::new)
                .map(Card::getPoints)
                .map(count -> (int) Math.pow(2, count - 1))
                .reduce(Math::addExact)
                .get();
    }

    public int step2(List<String> input) {
        List<Card> originalCards = input.stream()
                .map(l -> pattern.matcher(l))
                .filter(Matcher::matches)
                .map(Card::new)
                .toList();
        int total = 0;
        int[] extra = new int[originalCards.size()];
        for(Card c : originalCards){
            int bonus = extra[c.getId()-1];
            total= total + 1 + bonus;
            for(int i = 0; i<c.getPoints() && i+c.getId()<extra.length;i++){
                int current = extra[i+c.getId()];
                extra[c.getId()+i] = current + (1+bonus);
            }
        }
        return total;
    }

}

@Getter
@ToString
class Card {
    private final int id;
    private final Set<String> winning;
    private final Set<String> numbers;

    public Card(Matcher m) {
        id = Integer.parseInt(m.group(1));
        winning = new HashSet<>(asList(m.group(2).split(" +")));
        numbers = new HashSet<>(asList(m.group(3).split(" +")));
    }

    public int getPoints() {
        return (int) numbers.stream()
                .filter(n -> winning.contains(n))
                .count();
    }

    public Map<Integer, Integer> getExtraCards(int cap) {
        Map<Integer, Integer> set = new HashMap<>();
        for (int i = id + 1; i < id + 1 + getPoints() && i<=cap; i++) {
            set.put(i, 1);
        }
        return set;
    }
}