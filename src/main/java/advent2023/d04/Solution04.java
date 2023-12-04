package advent2023.d04;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

import static java.util.Arrays.asList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Solution04 {

    public int step1(List<String> input) {
        Pattern pattern = Pattern.compile("Card +\\d+: +([\\d ]+)\\| +([\\d ]+)");
        return input.stream()
                .map(l -> pattern.matcher(l))
                .filter(Matcher::matches)
                .map(m -> Card.builder()
                        .winning(new HashSet<>(asList(m.group(1).split(" +"))))
                        .numbers(new HashSet<>(asList(m.group(2).split(" +"))))
                        .build()
                )
                .peek(System.out::println)
                .map(card ->
                        card.getNumbers().stream()
                                .filter(n -> card.getWinning().contains(n))
                                .count()
                ).map(count -> (int) Math.pow(2, count-1))
                .reduce(Math::addExact)
                .get();
    }
}

@Builder
@Getter
@ToString
class Card {
    private final Set<String> winning;
    private final Set<String> numbers;
}