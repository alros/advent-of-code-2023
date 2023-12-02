package advent2023.d01;

import advent2023.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolution01 extends AbstractTest {

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(142, new Solution01().step1(input));
    }

    @Test
    public void actualStep1() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(53651, new Solution01().step1(input));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input03.txt");
        assertEquals(281, new Solution01().step2(input));
    }

    @Test
    public void testStep2mine() throws Exception {
        assertEquals(19, new Solution01().step2(Arrays.asList("1sevenine")));
    }

    @Test
    public void actualStep2() throws Exception {
        List<String> input = getInput("input04.txt");
        assertEquals(53894, new Solution01().step2(input));
    }

}
