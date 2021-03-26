package grep;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GrepTest {
    File output = new File("out.txt");

    public void changeStream() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(output, false);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
    }

    @Test
    public void easyTest() throws IOException {
        changeStream();
        Grep grep = new Grep(false, false, true);
        grep.search("kl", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected1.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void regexTest() throws IOException {
        changeStream();
        Grep grep = new Grep(true, false, false);
        grep.search("\\(.*\\)", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected2.txt")), Files.readAllLines(Path.of("out.txt")));
        changeStream();
        Grep grep1 = new Grep(false, false, false);
        grep1.search("\\(.*\\)", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected3.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void ignoreTest() throws IOException {
        changeStream();
        Grep grep = new Grep(false, false, true);
        grep.search("fs", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected4.txt")), Files.readAllLines(Path.of("out.txt")));
        changeStream();
        Grep grep1 = new Grep(false, false, false);
        grep1.search("fs", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected5.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void inversionTest() throws IOException {
        changeStream();
        Grep grep = new Grep(false, false, false);
        grep.search("$", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected6.txt")), Files.readAllLines(Path.of("out.txt")));
        changeStream();
        Grep grep1 = new Grep(false, true, false);
        grep1.search("$", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected7.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void allTest() throws IOException {
        changeStream();
        Grep grep = new Grep(true, true, true);
        grep.search(".*F.+F", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected8.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void changeTest() throws IOException {
        changeStream();
        Grep grep1 = new Grep(true, true, true);
        Grep grep2 = new Grep(false, false, false);
        grep1.search("\\d+", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected9.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test
    public void EmptyFile() throws IOException {
        changeStream();
        Grep grep = new Grep(true, true, true);
        grep.search("\\d+", "empty_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected10.txt")), Files.readAllLines(Path.of("out.txt")));
    }

    @Test(expected = FileNotFoundException.class)
    public void FileNotFound() throws IOException {
        changeStream();
        Grep grep = new Grep(true, true, true);
        grep.search("\\d+", "false.txt");
    }

    @Test(expected = java.util.regex.PatternSyntaxException.class)
    public void BakaRegex() throws IOException {
        changeStream();
        Grep grep = new Grep(true, false, false);
        grep.search("\\x{ffff", "easy_test.txt");
    }

    @Test
    public void EmptyString() throws IOException {
        changeStream();
        Grep grep = new Grep(true, false, false);
        grep.search("", "easy_test.txt");
        assertEquals(Files.readAllLines(Path.of("expected11.txt")), Files.readAllLines(Path.of("out.txt")));
    }
}