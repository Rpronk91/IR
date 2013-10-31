package indexer;

import shared.Token;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Indexer {

    private List<Token> index;

    /**
     * Creates an index on tokens contained in the root file provided.
     * @param root The path to the files to be indexed.
     */
    public Indexer(String root) {
        this.index = new ArrayList<>();

        int fileno = 0;
        for (File f : util.func.getDocumentCollection(root)) { // index each file.
            System.out.println(fileno++ + " " + f.getName().split("\\.")[0]);
            this.indexFile(f);
        }
        Collections.sort(index);
    }

    /**
     * Add the token contents of a given file to the index.
     * @param f The file to be indexed.
     */
    private void indexFile(File f) {
        String docID = f.getName().split("\\.")[0];
        LineSplitter t;
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            while ((line = br.readLine()) != null) {

                t = new LineSplitter(line);
                String word;
                while ( ( word = t.getToken()) != null) {
                    this.addToken(new Token(word, docID), docID);
                }
            }
            fis.close();
            br.close();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    /**
     * Will add the token if it is a new occurrence. If it is already contained in the index it will just increase its'
     * occurrence count in the given document.
     * @param t Token to be added.
     * @param docID The document the token appears in.
     */
    public void addToken(Token t, String docID) {
        int index = this.index.indexOf(t);
        if (index > 0) { // index.contains(t)
            this.index.get(index).addOccurence(docID);
        } else {
            this.index.add(t);
        }
    }

    /**
     * Creates the index file to be used later for retrieval.
     */
    public void outputIndex() {
        System.out.println( this.index.size() );
        try {
            FileOutputStream fos = new FileOutputStream("index.out");
            for (Token t : this.index) {
                fos.write((t.toString() + "\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Stop word elimination works under the assumption that the stop words are the top 10% most occurring words in the
     * corpus.
     */
    public void eliminateStopwords() {
        int tenPercent = (int) (0.1 * this.index.size());
        System.out.println(tenPercent);

        for (int i = 0; i < tenPercent; i++) {
            this.index.remove(this.index.size() - 1 - i);
        }
    }
}
