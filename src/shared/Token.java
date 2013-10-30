package shared;

import java.util.HashMap;

public class Token {
    private HashMap<String, Double> postingList;    /* <documentID, countAppearances in document> */
//    private final String str;                       /* may be obsolete  */
    private final String normalizedStr;

    public Token(String str, String docID) {
        this.postingList = new HashMap<>();
//        this.str = str;
        this.normalizedStr = util.StringNormalizer.normalize(str);
        this.postingList.put(docID, 1.0);
    }

    /**
     * TODO check lecture notes and make sure this corresponds to Df in them.
     * Returns the number of documents this token appears in.
     * @return the number of documents this token appears in.
     */
    public double getDf() {
        return this.postingList.size();
    }

    /**
     * TODO refactor>rename this function to the name that corresponds to lecture notes.
     * Returns the count of appearances of the token in a specific document.
     * @param docID the unique identifier of the document to look for.
     * @return the count of appearances of the token in a specific document.
     */
    public double getFrequency(String docID) {
        return this.postingList.get(docID);
    }

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
}
