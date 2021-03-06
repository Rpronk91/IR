package models;

import shared.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class resembles a container for instances of the Document class. Is used by the models. Provides an abstraction
 * concerning the container, handles the scores and anything else that is related to the Document class.
 */
public class DocumentCollection {
    private ArrayList<Document> docs;

    /**
     * Default Constructor : Populates the document collection with document instances as acquired from the collection
     * statistics file.
     */
    public DocumentCollection() {
        this.docs = new ArrayList<>();
        HashMap<String, Double> documentStatistics = util.func.readDocumentStatistics();
        for (String id : documentStatistics.keySet()) {
            this.docs.add( new Document(id, documentStatistics.get(id)) );
        }
    }

    /** Copy constructor  */
    public DocumentCollection(DocumentCollection other) {
        this.docs = new ArrayList<>();
        for (Document d : other.docs) {
            this.docs.add(new Document(d));
        }
    }

    /**
     * Sets a score of a given document by its' unique identifier.
     * @param docID The unique identifier of a document.
     * @param s The score to be assigned to the document.
     */
    public void setScore(String docID, double s) {
        Document dummy = new Document(docID, -1);
        this.docs.get( this.docs.indexOf(dummy) ).setScore(s);
    }

    public ArrayList<Document> getDocuments() { return this.docs; }

    /**
     * Returns the number of documents in the collection.
     * @return the number of documents in the collection.
     */
    public double getN() { return this.docs.size(); }

    /**
     * Returns the average length of the documents in the collection.
     * @return the average length of the documents in the collection.
     */
    public double getAverageLength() {
        double ret = 0.0;
        for (Document d : this.docs) {
            ret += d.getLength() / this.docs.size();
        } return ret;
    }

    public void sort() {
        Collections.sort(this.docs);
    }

    /**
     * Removes entries that do not have a positive value score from the collection.
     */
    public void eliminateInvalidScores() {
        for(Iterator<Document> i = this.docs.iterator(); i.hasNext();) {
            double score = i.next().getScore();
            if (Double.isNaN(score) || score <= 0.0) { i.remove(); }
        }
    }
}
