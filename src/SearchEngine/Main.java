package SearchEngine;

import indexer.CollectionParser;
import util.Settings;

public class Main {

    /**
     * Handles all the required methods for the indexing to take place.
     * @param root The root directory to be indexed.
     */
    private static void doIndexing(String root) {
        System.out.println("Indexing starts.");

        CollectionParser cp = new CollectionParser(root); // indexing complete

        cp.eliminateStopwords();                          // stopwords eliminated

        cp.outputIndex();                                 // index file created.

        cp.outputCollectionStatistics();                  // Collection stats file created.
    }

    public static void main(String[] args) {
        doIndexing(Settings.DOCUMENT_COLLECTION);
    }
}
