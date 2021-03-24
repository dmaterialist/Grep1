package grep;

import org.kohsuke.args4j.*;

import java.io.IOException;

public class GrepLauncher {
    @Option(name = "-r", metaVar = "regex")
    private boolean regex;
    @Option(name = "-v", metaVar = "inversion")
    private boolean inversion;
    @Option(name = "-i", metaVar = "ignore")
    private boolean ignore;
    @Argument(required = true, metaVar = "file", index = 0)
    private String word;
    @Argument(required = true, metaVar = "file", index = 1)
    private String file;

    public static void main(String[] args) throws IOException {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("invalid input");
            parser.printUsage(System.err);
        }
        Grep grep = new Grep(regex, inversion, ignore);
            grep.search(word, file);
    }
}
