package SearchEngine;

import indexer.CollectionParser;
import models.*;
import shared.Document;
import util.Settings;

import java.io.File;
import java.util.ArrayList;

public class Main {

    /**
     * Handles all the required methods for the indexing to take place.
     * @param root The root directory to be indexed.
     */
    private static void doIndexing(String root) {
        CollectionParser cp = new CollectionParser(root); // indexing complete

        cp.eliminateStopwords();                          // stop words eliminated

        cp.outputIndex();                                 // index file created.

        cp.outputCollectionStatistics();                  // Collection stats file created.
    }

    /**
     * TODO move this to SearchEngine.java and add a dialog for it. e.g. 'Wait while collection is being indexed'
     * Returns true if the index file and collection statistics files exist.
     * @return true if the index file and collection statistics files exist.
     */
    private static boolean indexingNotRequired() {
        File index = new File(Settings.INDEX_FNAME);
        File stats = new File(Settings.DOCUMENT_STATS_FNAME);
        return index.exists() && stats.exists();
    }

    public static void main(String[] args) {
        if (!indexingNotRequired()) {
            doIndexing(Settings.DOCUMENT_COLLECTION);
        }
    }
}
