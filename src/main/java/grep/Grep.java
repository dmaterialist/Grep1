package grep;

import java.io.*;

public class Grep {
    private static boolean regex;
    private static boolean inversion;
    private static boolean ignore;

    public Grep(Boolean regex, Boolean inversion, Boolean ignore) {
        Grep.regex = regex;
        Grep.inversion = inversion;
        Grep.ignore = ignore;
    }

    public static void search(String word, String file) throws IOException {
        if (!regex)
            word = "\\Q" + word + "\\E";
        if (ignore)
            word = "(?i)" + word + "(?-i)";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            File output = new File("out.txt");
            FileOutputStream fos = new FileOutputStream(output);
            PrintStream ps = new PrintStream(fos);
            PrintStream console = System.out;
            String line = reader.readLine();
            while (line != null) {
                if (inversion) {
                    if (!line.matches(".*" + word + ".*")) {
                        System.setOut(ps);
                        System.out.println(line);
                        System.setOut(console);
                        System.out.println(line);
                    }
                } else {
                    if (line.matches(".*" + word + ".*")) {
                        System.setOut(ps);
                        System.out.println(line);
                        System.setOut(console);
                        System.out.println(line);
                    }
                }
                line = reader.readLine();
            }
        }
    }
}