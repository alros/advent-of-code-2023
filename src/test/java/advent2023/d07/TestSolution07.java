package advent2023.d07;

import advent2023.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSolution07 extends AbstractTest {

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(6440, new Solution07().step1(input));
    }

    @Test
    public void testStep1alt() throws Exception {
        List<String> input = getInput("input03.txt");
        assertEquals(6592, new Solution07().step1(input));
    }

    @Test
    public void testStepActual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertTrue(249702704L> new Solution07().step1(input));
        assertEquals(249638405L, new Solution07().step1(input));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(5905, new Solution07().step2(input));
    }
    @Test
    public void testStep2alt() throws Exception {
        List<String> input = getInput("input03.txt");
        assertEquals(6839, new Solution07().step2(input));
    }

    @Test
    public void testStep2actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(249776650, new Solution07().step2(input));
    }

}
