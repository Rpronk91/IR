package indexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to be "Tokenizer". Renamed because the term Token got a new meaning now.
 */
public class LineSplitter {
    private Matcher m;
    private Pattern p;
    String line;

    public LineSplitter(String line) {
        this.p = Pattern.compile("[\\w']+");
        this.m = p.matcher(line);
        this.line = line;
    }

    public String getToken() {
        if(m.find()) { return line.substring(m.start(), m.end()); }
        return null;
    }
}
