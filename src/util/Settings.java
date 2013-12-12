package util;


/**
 * All the static variables shared throughout the project are located here.
 */
public class Settings {
    public static final String DOCUMENT_COLLECTION = "collection"; /* the root of the document collection. */

    public static final String DOCUMENT_STATS_FNAME= "collectionStats.out"; /* collection statistics filename */
    public static final String INDEX_FNAME         = "index.out";           /* index filename */
    public static final String STOPWRDS_FNAME      = "stopwords.out";       /* stopwords filename */

    public static final double stopword_ratio      = 0.05; /* Percentage of words that will be eliminated from index */

    public static final double BM25_k              = 0.5;
    public static final double BM25_b              = 0.5;
}
