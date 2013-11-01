package SearchEngine;

import indexer.Indexer;
import util.Settings;

public class Main {

    private static void doIndexing(String root) {
        long start = System.currentTimeMillis();
        System.out.println("Indexing starts.");

        Indexer i = new Indexer(root);

        System.out.println("Indexing complete.");

        i.eliminateStopwords();
        System.out.println("Stopwords eliminated.");

        i.outputIndex();
        System.out.println("Index file created.");

        System.out.println("Indexing complete in " + (System.currentTimeMillis() - start) / 1000 + " seconds");
    }

    public static void main(String[] args) {
        doIndexing(Settings.DOCUMENT_COLLECTION);
    }
}
