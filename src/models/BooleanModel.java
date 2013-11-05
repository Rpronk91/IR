package models;

import shared.Document;
import shared.Token;

import java.util.ArrayList;

public class BooleanModel extends Model {

    @Override
    protected void rank(String query) {
        ArrayList<Token> q = super.tokenizeQuery(query); // tokenize query

        for (Document doc : this.documentCollection) {
            double score = -1;

            // TODO

            doc.setScore(score);
        }
    }

    /**
     * Stub constructor
     */
    public BooleanModel() {
        super();
    }
}
