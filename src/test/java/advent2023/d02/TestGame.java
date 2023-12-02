package advent2023.d02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {

    @Test
    public void parseGame1() {
        String line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        Game game = new Game(line);
        assertEquals(1, game.getNumber());
        assertEquals(4, game.getRed());
        assertEquals(2, game.getGreen());
        assertEquals(6, game.getBlue());
    }

    @Test
    public void parseGame2() {
        String line = "Game 100: 8 red, 3 green; 4 green, 1 blue, 15 red; 10 red, 8 green, 1 blue";
        Game game = new Game(line);
        assertEquals(100, game.getNumber());
        assertEquals(15, game.getRed());
        assertEquals(8, game.getGreen());
        assertEquals(1, game.getBlue());
    }

    @Test
    public void parseGame3() {
        String line = "Game 20: 1 red";
        Game game = new Game(line);
        assertEquals(20, game.getNumber());
        assertEquals(1, game.getRed());
        assertEquals(0, game.getGreen());
        assertEquals(0, game.getBlue());
    }

    @Test
    public void parseGame4() {
        String line = "Game 20: 1 red; 12 red";
        Game game = new Game(line);
        assertEquals(20, game.getNumber());
        assertEquals(12, game.getRed());
        assertEquals(0, game.getGreen());
        assertEquals(0, game.getBlue());
    }

}
