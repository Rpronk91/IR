package shared;

import java.io.File;

public class Document implements Comparable<Document> {
    private final String id;
    private final double length;
    private double score;

    public Document(File f) {
        this.id = "";
        this.length = 0;
    }

    /**
     * Returns the length of the document in words.
     * @return the length of the document in words.
     */
    public double length() { return this.length; }

    /**
     * Returns the score assigned to this document.
     * @return the score assigned to this document.
     */
    public double score() { return this.score; }


    /**
     * Used to sort a container of this class after query evaluation.
     * @param other The document to compare to.
     * @return 1 if this > other, -1 if other > this, 0 otherwise.
     */
    @Override
    public int compareTo(Document other) {
        if (this.score == other.score) {
            return Double.compare(this.length, other.length);
        } else {
            return Double.compare(this.score, other.score);
        }
    }
}
