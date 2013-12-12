package models;

import indexer.LineSplitter;
import shared.Token;
import util.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This is the abstract class that need to be delegated by each model. All shared methods between models belong here.
 */
public abstract class Model {
    protected final DocumentCollection documentStatistics; /** Not to be changed. */
    protected DocumentCollection returnSet;
    protected Index index;
    protected HashSet<String> stopwords;

    /**
     * Ranks the document collection based on relevance acquired by the evaluation model subclass.
     * @param query The query to be evaluated.
     */
    protected abstract void scoreDocuments(String query);

    /** super Constructor. */
    public Model() {
        this.documentStatistics = new DocumentCollection();
        this.initStopWords();
        this.index = new Index();
    }

    /**
     * Ranks the document collection and returns a sorted collection of documents with non-zero scores to be presented
     * as results of the query.
     * @param query The query to be evaluated.
     * @return A sorted collection of documents to be presented as results to the query.
     */
    public DocumentCollection getRanking(String query) {
        this.returnSet = new DocumentCollection(this.documentStatistics); // clone
        this.scoreDocuments(query);
        returnSet.eliminateInvalidScores();
        returnSet.sort();
        return returnSet;
    }

    /**
     * Split the query on whitespace and return a container with tokens.
     * @param query The string to be processed.
     * @return a container with tokens of the query.
     * TODO no logic in repetition of tokens etc, works for Boolean
     */
    protected ArrayList<Token> tokenizeQuery(String query) {
        ArrayList<Token> ret = new ArrayList<>();

        LineSplitter ls = new LineSplitter(query);

        String word;
        while ( (word = ls.getToken()) != null) {
            if ( this.stopwords.contains(word) ) { continue; }

            Token token = new Token(word, "query");
            if ( !ret.contains(token)) {
                ret.add(token);
            }
        }
        return ret;
    }

    /**
     * Reads the stopword file and initializes the stopword container
     */
    private void initStopWords() {
        this.stopwords = new HashSet<>();

        try {
            File f = new File(Settings.STOPWRDS_FNAME);
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                Token temp = new Token(line);

                this.stopwords.add( temp.getNormalizedString() );
            }
            fis.close(); br.close();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    @Override
    public String toString() { return this.getClass().getSimpleName(); }
}