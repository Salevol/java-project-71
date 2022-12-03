package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        version = "gendiff 0.1")
public final class App implements Callable<Integer> {
    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean version;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]", paramLabel = "format")
    private String format;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    String filePath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    String filePath2;

    @Override
    public Integer call() throws IOException {
        String formattedDiff = Differ.generate(filePath1, filePath2, this.format);
        System.out.println(formattedDiff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
