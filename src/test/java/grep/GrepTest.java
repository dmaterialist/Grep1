package grep;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GrepTest {
    @Test
    public void easyTest() throws IOException {
        Grep grep = new Grep(false, false, true);
        grep.search("kl", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected1.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void regexTest() throws IOException {
        Grep grep = new Grep(true, false, false);
        grep.search("\\(.*\\)", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected2.txt")), Files.readAllLines(Path.of("out.txt")));
        Grep grep1 = new Grep(false, false, false);
        grep1.search("\\(.*\\)", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected3.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void ignoreTest() throws IOException {
        Grep grep = new Grep(false, false, true);
        grep.search("fs", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected4.txt")), Files.readAllLines(Path.of("out.txt")));
        Grep grep1 = new Grep(false, false, false);
        grep1.search("fs", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected5.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void inversionTest() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.search("$", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected6.txt")), Files.readAllLines(Path.of("out.txt")));
        Grep grep1 = new Grep(false, true, false);
        grep1.search("$", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected7.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void allTest() throws IOException {
        Grep grep = new Grep(true, true, true);
        grep.search(".*F.+F", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected8.txt")), Files.readAllLines(Path.of("out.txt")));
    }
}