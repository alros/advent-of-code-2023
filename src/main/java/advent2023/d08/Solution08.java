package advent2023.d08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution08 {

    private static final Pattern pattern = Pattern.compile("([A-Z\\d]{3}) = \\(([A-Z\\d]{3}), ([A-Z\\d]{3})\\)");

    public long step1(List<String> input) {
        char[] path = input.get(0).toCharArray();
        Map<String, String[]> map = parseMap(input);
        return solve("AAA", map, path, 1);
    }

    public long step2(List<String> input) {
        char[] path = input.get(0).toCharArray();
        Map<String, String[]> map = parseMap(input);
        return map.keySet().stream()
                .filter(k -> k.endsWith("A"))
                .map(pos -> solve(pos, map, path, 2))
                .reduce(1L, (a, b) -> lcm(a, b));
    }

    private static Map<String, String[]> parseMap(List<String> input) {
        Map<String, String[]> map = new HashMap<>();
        for (int i = 2; i < input.size(); i++) {
            Matcher matcher = pattern.matcher(input.get(i));
            assert matcher.matches();
            map.put(matcher.group(1), new String[]{matcher.group(2), matcher.group(3)});
        }
        return map;
    }

    private static long solve(String pos, Map<String, String[]> map, char[] path, int step) {
        int steps = 0;
        while ((step == 1 && !pos.equals("ZZZ")) || (step == 2 && !pos.endsWith("Z"))) {
            pos = map.get(pos)[path[steps % path.length] == 'R' ? 1 : 0];
            steps++;
        }
        return steps;
    }

    public static long lcm(long a, long b) {
        assert a != 0 && b != 0;
        long max = Math.max(Math.abs(a), Math.abs(b));
        long min = Math.min(Math.abs(a), Math.abs(b));
        long lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }

}