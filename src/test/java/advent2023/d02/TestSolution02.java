package advent2023.d02;

import advent2023.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSolution02 extends AbstractTest {

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(8, new Solution02().step1(input, 12, 13, 14));
    }

    @Test
    public void testStep1a() throws Exception {
        List<String> input = Arrays.asList("Game 51: 12 red");
        assertEquals(51, new Solution02().step1(input, 12, 13, 14));
    }

    @Test
    public void testStep1b() throws Exception {
        List<String> input = Arrays.asList("Game 51: 13 red");
        assertEquals(0, new Solution02().step1(input, 12, 13, 14));
    }

    @Test
    public void testStep1c() throws Exception {
        List<String> input = Arrays.asList("Game 51: 12 red; 1 red");
        assertEquals(51, new Solution02().step1(input, 12, 13, 14));
    }

    @Test
    public void testStep1Actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(2204, new Solution02().step1(input, 12, 13, 14));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(2286, new Solution02().step2(input, 12, 13, 14));
    }

    @Test
    public void testStep2Actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(71036, new Solution02().step2(input, 12, 13, 14));
    }
}
