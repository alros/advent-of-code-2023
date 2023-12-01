package advent2023.d01;

import java.util.List;
import java.util.Map;

public class Solution01 {

    public int step1(List<String> input) {
        return input.stream().map(line -> {
            int first = -1, last = 1;
            for (char c : line.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    if (first == -1) {
                        first = c - '0';
                        last = first;
                    } else {
                        last = c - '0';
                    }
                }
            }
            return first * 10 + last;
        }).reduce(Integer::sum).get();
    }

    public int step2(List<String> input) {
        Map<String, String> replacements = Map.of(
                "1", "one",
                "2", "two",
                "3", "three",
                "4", "four",
                "5", "five",
                "6", "six",
                "7", "seven",
                "8", "eight",
                "9", "nine");
        List<String> newInput = input.stream().map(l -> {
            for (String num : replacements.keySet()) {
                String numStr = replacements.get(num);
                if (l.indexOf(numStr) >= 0) {
                    l = l.replace(numStr, numStr.substring(0, 1) + num + numStr.substring(2));
                }
            }
            return l;
        }).toList();
        return step1(newInput);
    }
}