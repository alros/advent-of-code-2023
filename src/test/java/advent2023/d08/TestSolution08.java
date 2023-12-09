package advent2023.d08;

import advent2023.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolution08 extends AbstractTest {

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(2, new Solution08().step1(input));
    }

    @Test
    public void testStep1alt() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(6, new Solution08().step1(input));
    }

    @Test
    public void testStep1actual() throws Exception {
        List<String> input = getInput("input03.txt");
        assertEquals(24253, new Solution08().step1(input));
    }

    @Test
    public void testStep2() throws Exception {
        List<String> input = getInput("input04.txt");
        assertEquals(6, new Solution08().step2(input));
    }

    @Test
    public void testStep2actual() throws Exception {
        List<String> input = getInput("input03.txt");
        assertEquals(12357789728873L, new Solution08().step2(input));
    }

}
