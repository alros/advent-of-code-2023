package advent2023.d02;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

@Getter
public class Game {

    private final int number;
    private final int red;
    private final int green;
    private final int blue;

    private static final Pattern extractGame = Pattern.compile("Game (\\d+): (.*)");

    public Game(String line) {
        Matcher matcher = extractGame.matcher(line);
        matcher.matches();

        number = Integer.parseInt(matcher.group(1));

        int[] extractions = asList(matcher.group(2).split("; ")).stream().map(e -> {
            String[] balls = e.split(", ");
            int[] qty = new int[3];
            for (String ball : balls) {
                String[] numColor = ball.split(" ");
                int q = Integer.parseInt(numColor[0]);
                switch (numColor[1]) {
                    case "red":
                        qty[0] = q;
                        break;
                    case "green":
                        qty[1] = q;
                        break;
                    case "blue":
                        qty[2] = q;
                        break;
                }
            }
            return qty;
        }).reduce((a, b) -> {
            for(int i=0;i<a.length;i++){
                a[i] = Math.max(a[i],b[i]);
            }
            return a;
        }).get();
        this.red = extractions[0];
        this.green = extractions[1];
        this.blue = extractions[2];
    }
}
