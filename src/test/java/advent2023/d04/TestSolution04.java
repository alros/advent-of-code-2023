package advent2023.d04;

import advent2023.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolution04 extends AbstractTest {

    @Test
    public void testStep1Line() throws Exception {
        List<String> input = Arrays.asList("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
        assertEquals(8, new Solution04().step1(input));
    }

    @Test
    public void testStep1Line2() throws Exception {
        List<String> input = Arrays.asList("Card   1:  1  2 |  1  2");
        assertEquals(2, new Solution04().step1(input));
    }

    @Test
    public void testStep2Lines() throws Exception {
        List<String> input = Arrays.asList(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
        assertEquals(10, new Solution04().step1(input));
    }

    @Test
    public void testStep3Lines() throws Exception {
        List<String> input = Arrays.asList(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
        assertEquals(12, new Solution04().step1(input));
    }

    @Test
    public void testStep4Lines() throws Exception {
        List<String> input = Arrays.asList(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
        assertEquals(13, new Solution04().step1(input));
    }

    @Test
    public void testStep5Lines() throws Exception {
        List<String> input = Arrays.asList(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
        assertEquals(13, new Solution04().step1(input));
    }

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(13, new Solution04().step1(input));
    }

    @Test
    public void testStep1Actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(24706, new Solution04().step1(input));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(30, new Solution04().step2(input));
    }

    @Test
    public void testStep2Actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(13114317, new Solution04().step2(input));
    }

}
