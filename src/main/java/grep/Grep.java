package grep;

import java.io.*;

public class Grep {
    private final boolean regex;
    private final boolean inversion;
    private final boolean ignore;

    public Grep(Boolean regex, Boolean inversion, Boolean ignore) {
        this.regex = regex;
        this.inversion = inversion;
        this.ignore = ignore;
    }

    public void search(String word, String file) throws IOException {
        if (word.isEmpty()) {
            System.out.println("You entered empty word");
            return;
        }
        if (!regex)
            word = "\\Q" + word + "\\E";
        if (ignore)
            word = "(?i)" + word + "(?-i)";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        if (line == null) {
            System.out.println("Your file is empty");
            return;
        }
        while (line != null) {
            if (inversion) {
                if (!line.matches(".*" + word + ".*")) {
                    System.out.println(line);
                }
            } else {
                if (line.matches(".*" + word + ".*")) {
                    System.out.println(line);
                }
            }
            line = reader.readLine();
        }
    }
}