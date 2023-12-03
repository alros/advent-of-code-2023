package advent2023.d03;

import advent2023.d02.Game;

import java.util.List;
import java.util.function.IntFunction;

public class Solution03 {

    public int step1(List<String> input) {
        char[][] map = buildMap(input);
        int total = 0;
        for(int y = 0; y < map.length; y++) {
            int accumulator = 0;
            boolean keep = false;
            for(int x = 0; x < map[y].length; x++) {
                int v = map[y][x] - '0';
                if(v >=0 && v <=9){
                    accumulator = accumulator * 10 + v;
                    for(int j = Math.max(0, y-1);j<Math.min(map.length,y+2);j++){
                        for(int i = Math.max(0, x-1);i<Math.min(map[y].length, x+2); i++){
                            char c = map[j][i];
                            if(c !='.' && (c>'9' || c<'0')){
                                keep = true;
                            }
                        }
                    }
                } else {
                    if(keep){
                        total += accumulator;
                        keep = false;
                    }
                    accumulator = 0;
                }
            }
            if(keep){
                total += accumulator;
                keep = false;
            }
            accumulator = 0;
        }
        return total;
    }

    private char[][] buildMap(List<String> input) {
        return input.stream().map(String::toCharArray).toList().toArray(new char[0][0]);
    }

    public int step2(List<String> input){
        return 0;
    }
}