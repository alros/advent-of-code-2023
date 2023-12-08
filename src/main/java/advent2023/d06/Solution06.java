package advent2023.d06;

import java.util.List;
import java.util.*;


public class Solution06 {

    public int step1(List<String> input) {
        List<Integer> times = parseLineStep1(input.get(0));
        List<Integer> distances = parseLineStep1(input.get(1));

        int solutions = 1;
        for(int i = 0; i<times.size(); i++){
            solutions *= beat(times.get(i), distances.get(i));
        }

        return solutions;
    }

    private List<Integer> parseLineStep1(String line){
        return Arrays.asList(line.split(" +"))
                .stream()
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .toList();
    }

    public int step2(List<String> input){
        long time = parseLineStep2(input.get(0));
        long distance = parseLineStep2(input.get(1));
        return beat(time, distance);
    }

    private long parseLineStep2(String line){
        return Long.parseLong(Arrays.asList(line.split(" +"))
                .stream()
                .filter(s -> s.matches("\\d+"))
                .reduce("",(a,b)->a+b));
    }

    private int beat(long time, long distance) {
        int solutions = 0;
        for(int i = 1; i<time; i++){
            if(i*(time-i)>distance){
                solutions++;
            }else if(solutions>0){
                break;
            }
        }
        return solutions;
    }

}