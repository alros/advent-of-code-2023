package advent2023;

import advent2023.d02.TestSolution02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractTest {

    protected List<String> getInput(String file) throws URISyntaxException, IOException {
        URL resource = TestSolution02.class.getResource(file);
        assert resource != null;
        Path path = Paths.get(resource.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
