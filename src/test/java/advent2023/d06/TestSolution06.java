package advent2023.d06;

import advent2023.AbstractTest;
import advent2023.d04.Solution04;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolution06 extends AbstractTest {

    @Test
    public void testStep1case2() throws Exception {
        List<String> input = List.of("a: 15", "b: 40");
        assertEquals(8, new Solution06().step1(input));
    }

    @Test
    public void testStep1Line() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(288, new Solution06().step1(input));
    }

    @Test
    public void testStep1actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(345015, new Solution06().step1(input));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(71503, new Solution06().step2(input));
    }

    @Test
    public void testStep2actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(42588603, new Solution06().step2(input));
    }
}
