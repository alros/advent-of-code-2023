package advent2023.d03;

import advent2023.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolution03 extends AbstractTest {

    @Test
    public void testStep1() throws Exception {
        List<String> input = getInput("input01.txt");
        assertEquals(4361, new Solution03().step1(input));
    }
    @Test
    public void testStep1Actual() throws Exception {
        List<String> input = getInput("input02.txt");
        assertEquals(527364, new Solution03().step1(input));
    }

}
