package models;

import shared.Document;
import shared.Token;

import java.util.ArrayList;

public class TFIDFModel extends Model {

    /** Stub constructor  */
    public TFIDFModel() {
        super();
    }

    @Override
    protected void scoreDocuments(ArrayList<Token> queryTokens) {

        for (Document d : this.documents.getDocuments()) {
            double score = 0.0;



            documents.setScore(d.getID(), score);
        }
    }
}
