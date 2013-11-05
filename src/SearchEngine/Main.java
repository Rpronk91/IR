package SearchEngine;

import indexer.CollectionParser;
import models.BooleanModel;
import models.Model;
import util.Settings;

import java.util.ArrayList;

public class Main {

    /**
     * Handles all the required methods for the indexing to take place.
     * @param root The root directory to be indexed.
     */
    private static void doIndexing(String root) {
        System.out.println("Indexing starts.");

        CollectionParser cp = new CollectionParser(root); // indexing complete

        cp.eliminateStopwords();                          // stop words eliminated

        cp.outputIndex();                                 // index file created.

        cp.outputCollectionStatistics();                  // Collection stats file created.
    }

    private static void doQuerying() {
        ArrayList<Model> models = new ArrayList<>();
        models.add( new BooleanModel() );
    }

    public static void main(String[] args) {
        doIndexing(Settings.DOCUMENT_COLLECTION);

        doQuerying();
    }
}
