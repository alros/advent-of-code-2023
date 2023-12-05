package advent2023.d05;

import advent2023.AbstractTest;
import advent2023.d04.Solution04;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolution05 extends AbstractTest {

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(35L, new Solution05().step1(input));
    }
    @Test
    public void testStep1actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(265018614L, new Solution05().step1(input));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(46L, new Solution05().step2(input));
    }

    @Test
    public void testStep2actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(-1, new Solution05().step2(input));
    }

}
