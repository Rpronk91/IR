package indexer;

import shared.Token;
import util.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Parses a given document collection and holds the index and the document collection statistics for the files contained
 * in the collection.
 */
public class CollectionParser {

    private List<Token> index;
    private HashMap<String, Double> collectionStatistics;

    /**
     * Creates an index on tokens contained in the root file provided.
     * @param root The path to the files to be indexed.
     */
    public CollectionParser(String root) {
        this.index = new ArrayList<>();
        this.collectionStatistics = new HashMap<>();

        long start = System.currentTimeMillis();
        int fileno = 0;
        System.out.print("Indexing [");

        for (File f : util.func.getDocumentCollection(root)) { // index each file.
            if (fileno++ % 10 == 0) { System.out.print("."); }

            String fileID = f.getName().split("\\.")[0];
            double tokenCount = this.indexFile(f);
            this.collectionStatistics.put(fileID, tokenCount);
        }
        System.out.println("] Finished in " + (System.currentTimeMillis() - start) / 1000 + " seconds");
        Collections.sort(this.index);
    }

    /**
     * Add the token contents of a given file to the index.
     * @param f The file to be indexed.
     * @return The length of the document in tokens.
     */
    private double indexFile(File f) {
        double tokenCount = 0;
        String docID = f.getName().split("\\.")[0];
        LineSplitter t;
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                t = new LineSplitter(line);
                String word;
                while (( word = t.getToken() ) != null) {
                    this.addToken(new Token(word, docID), docID);
                    tokenCount++;
                }
            }
            fis.close(); br.close();
        } catch (IOException ex) { ex.printStackTrace(); }
        return tokenCount;
    }

    /**
     * Will add the token if it is a new occurrence. If it is already contained in the index it will just increase its'
     * occurrence count in the given document.
     * @param t Token to be added.
     * @param docID The document the token appears in.
     */
    public void addToken(Token t, String docID) {
        int i = this.index.indexOf(t);
        if (i > -1) { // index does contain Token t
            this.index.get(i).addOccurrence(docID);
        } else {
            this.index.add(t);
        }
    }

    /**
     * Stop word elimination works under the assumption that the stop words are the top 10% most occurring words in the
     * corpus.
     */
    public void eliminateStopwords() {
        Collections.sort(this.index);
        int tenPercent = (int) (0.1 * this.index.size());

        for (int i = 0; i < tenPercent; i++) {
            this.index.remove(this.index.size() - 1 - i);
        } System.out.println("Stop words eliminated: " + tenPercent);
    }

    /**
     * Creates the index file to be used later for retrieval.
     */
    public void outputIndex() {
        System.out.println( this.index.size() );
        try {
            FileOutputStream fos = new FileOutputStream(Settings.INDEX_FNAME);
            for (Token t : this.index) {
                fos.write((t.toString() + "\n").getBytes());
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Creates the document that holds statistics about the document collection.
     */
    public void outputCollectionStatistics() {
        try {
            FileOutputStream fos = new FileOutputStream(Settings.DOCUMENT_STATS_FNAME);
            for (String docID : this.collectionStatistics.keySet()) {
                String documentLength = " " + this.collectionStatistics.get(docID);
                fos.write((docID + documentLength + "\n").getBytes());
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
