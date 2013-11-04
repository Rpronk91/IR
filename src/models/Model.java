package models;

import shared.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This is the abstract class that need to be delegated by each model. All shared methods between models belong here.
 */
public abstract class Model {
    Collection<Document> documentCollection;

    /**
     * Ranks the document collection based on relevance acquired by the evaluation model subclass.
     * @param query The query to be evaluated.
     */
    protected abstract void rank(String query);

    /** super Constructor. */
    public Model() {
        this.documentCollection = new ArrayList<>();
    }

    /**
     * Ranks the document collection and returns a sorted collection of documents to be presented as results to the query.
     * @param query The query to be evaluated.
     * @return A sorted collection of documents to be presented as results to the query.
     */
    public Collection<Document> getRanking(String query) {
        this.rank(query);
        ArrayList<Document> ret = new ArrayList<>(this.documentCollection);
        Collections.sort(ret);
        return ret;
    }
}