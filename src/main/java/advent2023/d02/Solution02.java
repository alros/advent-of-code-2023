package advent2023.d02;

import java.util.List;

public class Solution02 {

    public int step1(List<String> input, int red, int green, int blue) {
        return input.stream()
                .map(l-> new Game(l))
                .filter(g -> g.getRed()<=red && g.getGreen()<=green && g.getBlue()<=blue)
                .map(Game::getNumber)
                .reduce(Integer::sum)
                .orElseGet(()->0);
    }

    public int step2(List<String> input, int red, int green, int blue){
        return input.stream()
                .map(l-> new Game(l))
                .map(g -> g.getRed() * g.getGreen() * g.getBlue())
                .reduce(Integer::sum)
                .orElseGet(() -> 0);
    }
}