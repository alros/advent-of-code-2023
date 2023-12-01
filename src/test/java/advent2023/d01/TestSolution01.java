package advent2023.d01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSolution01 {

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

    private List<String> getInput(String file) throws URISyntaxException, IOException {
        URL resource = TestSolution01.class.getResource(file);
        assert resource != null;
        Path path = Paths.get(resource.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
