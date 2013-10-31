package shared;

import java.util.HashMap;

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
     * Parsing constructor TODO
     * @param line The line to be parsed into a token instance.
     */
    public Token(String line) {
        this.normalizedStr = "";
    }

    /**
     * Document frequency is the number of documents in the collection that the term occurs in.
     * @return the number of documents this token occurs in.
     */
    public double getDF() { return this.postingList.size(); }

    /**
     * The term frequency tf(t,d) of term t in document d is defined as the number of times that t occurs in d
     * @param docID the unique identifier of the document to look for.
     * @return the count of appearances of the token in a specific document.
     */
    public double getTF(String docID) { return this.postingList.get(docID); }

    /**
     * Increase occurence count in posting list.
     * @param docID The id of the document to be increased.
     */
    public void addOccurence(String docID) {
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
    public String toString() {
        String ret = this.normalizedStr;
        for (String s : this.postingList.keySet()) {
            ret += " " + s;
        } return ret;
    }

    @Override
    /**
     * We want the container to be sorted on the DF so we can eliminate the stop words and make the search faster.
     */
    public int compareTo(Token other) {
        if (this.getDF() < other.getDF())   { return -1; }
        if (this.getDF() > other.getDF())   { return 1; }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
