package SearchEngine;

import indexer.CollectionParser;
import models.*;
import shared.Document;
import util.Settings;

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

    private static void doQuerying() {
        ArrayList<Model> models = new ArrayList<>();
        models.add( new BooleanModel() );
        models.add( new TFIDFModel() );
        models.add( new BM25Model() );

//        DocumentCollection res = models.get(0).getRanking("manufacturingmanufacturingmaterialsmateri");
        DocumentCollection res = models.get(1).getRanking("manufacturingmanufacturingmaterialsmateri");
//        DocumentCollection res = models.get(2).getRanking("manufacturingmanufacturingmaterialsmateri");
        for (Document d : res.getDocuments()) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
//        doIndexing(Settings.DOCUMENT_COLLECTION);

        doQuerying();
    }
}
