package indexer;

import util.StaticVars;

public class IndexerMain {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Indexing starts.");
        Indexer i = new Indexer(StaticVars.DOCUMENT_COLLECTION);
        System.out.println("Indexing complete.");

        i.outputIndex();
        System.out.println("Index file created.");


        System.out.println("Indexing complete in " + (System.currentTimeMillis() - start) / 1000 + " seconds");
    }
}
