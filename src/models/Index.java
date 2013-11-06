package models;

import shared.Token;
import util.Settings;

import java.io.*;
import java.util.ArrayList;

/**
 * This class contains the index as will be parsed and used by the models.
 */
public class Index {
    private ArrayList<Token> index;

    /**
     * Default Constructor : Parses the index output file and initializes the index accordingly.
     */
    public Index() {
        this.index = new ArrayList<>();

        try {
            File f = new File(Settings.INDEX_FNAME);
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                this.index.add( new Token(line) );
            }
            fis.close(); br.close();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    /**
     * Return the token from the index that corresponds to a given query token
     * @param t The query token.
     * @return a reference to the token in the index.
     */
    public Token getToken(Token t) {
        int i = this.index.indexOf(t);
        return this.index.get( i );
    }

    public boolean contains(Token t) {
        return this.index.contains(t);
    }
}
