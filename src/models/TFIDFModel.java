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
    protected void scoreDocuments(String query) {
        ArrayList<Token> queryTokens = this.tokenizeQuery(query);
        for (Document d : this.documents.getDocuments()) {
            double score = 0.0;

            for (Token t : queryTokens) {
                Token indexToken = this.index.getToken(t);
                if (indexToken == null) { continue; } // token not in index
                score += (1 + t.getTF(d.getID()) );
            }

            documents.setScore(d.getID(), score);
        }
    }
}
