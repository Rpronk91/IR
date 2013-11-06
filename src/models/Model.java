package models;

import indexer.LineSplitter;
import shared.Document;
import shared.Token;
import util.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * This is the abstract class that need to be delegated by each model. All shared methods between models belong here.
 */
public abstract class Model {
    protected ArrayList<Document> documentCollection;
    protected ArrayList<Token> index;

    /**
     * Ranks the document collection based on relevance acquired by the evaluation model subclass.
     * @param query The query to be evaluated.
     */
    protected abstract void rank(String query);

    /** super Constructor. */
    public Model() {
        this.documentCollection = new ArrayList<>();
        this.index = new ArrayList<>();
        this.initDocumentCollection();
        this.initIndex();
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
     * Parses the index output file and initializes the index accordingly.
     */
    protected void initIndex() {
        try {
            File f = new File(Settings.INDEX_FNAME);
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            while ((line = br.readLine()) != null) {
                this.index.add( new Token(line) );
            }
            fis.close(); br.close();
        } catch (IOException ex) { ex.printStackTrace(); }
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
            if ( !ret.contains(token) ) {
                ret.add(token);
            }
        } return ret;
    }
}