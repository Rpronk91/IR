package shared;

public class Document implements Comparable<Document> {
    private final String id;
    private final double length;
    private double score;

    /**
     * Constructor
     * @param id The unique identifier of the document.
     * @param length The length of the document.
     */
    public Document(String id, double length) {
        this.id = id;
        this.length = length;
        this.score = 0;
    }

    /** Copy Constructor. */
    public Document(Document other) {
        this.id = other.id;
        this.length = other.length;
        this.score = other.score;
    }

    /**
     * Returns the length of the document in words.
     * @return the length of the document in words.
     */
    public double getLength() { return this.length; }

    /**
     * Returns the score assigned to this document.
     * @return the score assigned to this document.
     */
    public double getScore() { return this.score; }
    public void setScore(double score) { this.score = score; }

    /**
     * Returns the unique identifier of this document.
     * @return the unique identifier of this document.
     */
    public String getID() { return this.id; }

    @Override
    public String toString() {
        String ret = this.id + " " + this.score;
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (! (obj instanceof Document)) { return false; }

        Document other = (Document) obj;
        return this.id.equals(other.id);
    }

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
            return -Double.compare(this.score, other.score);
        }
    }
}
