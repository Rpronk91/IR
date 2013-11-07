package shared;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token implements Comparable<Token> {
    private HashMap<String, Double> postingList;    /* <documentID, countAppearances in document> */
//    private final String str;                       /* may be obsolete  */
    private final String normalizedStr;

    /**
     * Constructor
     * @param str Token string.
     * @param docID Document in which the token appears.
     */
    public Token(String str, String docID) {
        this.postingList = new HashMap<>();
        this.normalizedStr = util.StringNormalizer.normalize(str);
        this.postingList.put(docID, 1.0);
    }

    /**
     * Index file parsing constructor
     * @param line The line to be parsed into a token instance. e.g.:
     *             word	{docID, count}
     */
    public Token(String line) {
        this.postingList = new HashMap<>();

        String[] split = line.split("\t");
        this.normalizedStr = split[0];
        this.postingList = this.parsePostingList(split[1]);
    }

    /**
     * Parses a right-hand-side string that comes from the index file to return a posting list to be used for the token.
     * @param str The list string.
     * @return Posting list hashmap.
     */
    private HashMap<String, Double> parsePostingList(String str) {
        HashMap<String, Double> ret = new HashMap<>();

        Pattern p = Pattern.compile("\\{([^\\}]+)\\}");
        Matcher m = p.matcher(str);

        while (m.find()) {
            String[] singleDoc = str.substring(m.start(), m.end()).split(",");
            String docID = singleDoc[0].substring(1);
            Double freq = Double.parseDouble(singleDoc[1].substring(0, singleDoc[1].length() - 1));
            ret.put(docID, freq);
        } return ret;
    }

    /**
     * Document frequency is the number of documents in the collection that the term occurs in.
     * @return the number of documents this token occurs in.
     */
    public double getDF() { return this.postingList.size(); }

    public String getNormalizedString() { return this.normalizedStr; }

    /**
     * The term frequency tf(t,d) of term t in document d is defined as the number of times that t occurs in d
     * @param docID the unique identifier of the document to look for.
     * @return the count of appearances of the token in a specific document.
     */
    public double getTF(String docID) {
        if (this.postingList.containsKey(docID)) { return this.postingList.get(docID); }
        else { return Double.NaN; }

    }

    public HashMap<String, Double> getPostingList() {
        return this.postingList;
    }

    /**
     * Increase occurrence count in posting list.
     * @param docID The id of the document to be increased.
     */
    public void addOccurrence(String docID) {
        double prevValue = 0;
        if (this.postingList.get(docID) != null) {
            prevValue = this.postingList.get(docID);
        }
        this.postingList.put(docID, prevValue + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (! (obj instanceof Token)) { return false; }
        Token other = (Token) obj;

        return this.normalizedStr.equals(other.normalizedStr);
    }

    @Override
    /**
     * The toString function as will be used for the index file.
     */
    public String toString() {
        String ret = this.normalizedStr + "\t";
        for (String s : this.postingList.keySet()) {
            ret += "{" + s + ", " + this.postingList.get(s) + "}";
        } return ret;
    }

    @Override
    /**
     * We want the container to be sorted on the DF so we can eliminate the stop words and make the search faster.
     */
    public int compareTo(Token other) {
        if (this.getDF() < other.getDF())   { return -1; }
        if (this.getDF() > other.getDF())   { return 1; }
        return 0;
    }
}
