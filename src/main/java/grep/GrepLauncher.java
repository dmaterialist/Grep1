package grep;
import org.kohsuke.args4j.*;

import java.io.IOException;

public class GrepLauncher {
    @Option(name = "-r", metaVar = "regex", required = false)
    private Boolean regex;
    @Option(name = "-v", metaVar = "inversion", required = false)
    private Boolean inversion;
    @Option(name = "-i", metaVar = "ignore", required = false)
    private Boolean ignore;
    @Argument(required = true, metaVar = "file")
    private String word;
    @Argument(required = true, metaVar = "file")
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
        Grep.search(file, word);
    }
}
