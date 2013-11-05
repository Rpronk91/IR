package models;

import indexer.LineSplitter;
import shared.Document;
import shared.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * This is the abstract class that need to be delegated by each model. All shared methods between models belong here.
 */
public abstract class Model {
    ArrayList<Document> documentCollection;

    /**
     * Ranks the document collection based on relevance acquired by the evaluation model subclass.
     * @param query The query to be evaluated.
     */
    protected abstract void rank(String query);

    /** super Constructor. */
    public Model() {
        this.documentCollection = new ArrayList<>();
        initDocumentCollection();
    }

    /**
     * Populates the document collection with document instances as acquired from the collection statistics file.
     */
    private void initDocumentCollection() {
        HashMap<String, Double> documentStatistics = util.func.readDocumentStatistics();
        for (String id : documentStatistics.keySet()) {
            documentCollection.add( new Document(id, documentStatistics.get(id)) );
        }
    }

    /**
     * Ranks the document collection and returns a sorted collection of documents to be presented as results to the query.
     * @param query The query to be evaluated.
     * @return A sorted collection of documents to be presented as results to the query.
     */
    public Collection<Document> getRanking(String query) {
        this.rank(query);
        Collections.sort(this.documentCollection);
        return this.documentCollection;
    }

    /**
     * Split the query on whitespace and return a container with tokens.
     * @param query The string to be processed.
     * @return a container with tokens of the query.
     * TODO no logic in repetition of tokens etc works for Boolean
     */
    protected ArrayList<Token> tokenizeQuery(String query) {
        ArrayList<Token> ret = new ArrayList<>();

        LineSplitter ls = new LineSplitter(query);

        String word;
        while ( (word = ls.getToken()) != null) {
            Token token = new Token(word, "query");
            if ( !ret.contains(token) ){
                ret.add(token);
            }
        }
        return ret;
    }

//    /**
//     * Will add the token if it is a new occurrence. If it is already contained in the index it will just increase its'
//     * occurrence count in the given document.
//     * @param t Token to be added.
//     * @param docID The document the token appears in.
//     */
//    public void addToken(Token t, String docID) {
//        int index = this.index.indexOf(t);
//        if (index > 0) { // index.contains(t)
//            this.index.get(index).addOccurence(docID);
//        } else {
//            this.index.add(t);
//        }
//    }
}