package models;

import shared.Document;
import shared.Token;

import java.util.ArrayList;

public class TFIDFModel extends Model {

    /** Stub constructor  */
    public TFIDFModel() { super(); }

    @Override
    protected void scoreDocuments(String query) {
        ArrayList<Token> queryTokens = this.tokenizeQuery(query);
        for (Document d : this.documents.getDocuments()) {
            double score = 0.0;

            for (Token t : queryTokens) {
                Token indexToken = this.index.getToken(t);
                if (indexToken == null) { continue; } // token not in index
                double left = 1 + Math.log(indexToken.getTF(d.getID()));
                double idf = Math.log(this.documents.getN() / indexToken.getDF());
                score += left * idf;
            }
            documents.setScore(d.getID(), score);
        }
    }
}
