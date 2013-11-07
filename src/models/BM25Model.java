package models;

import shared.Document;
import shared.Token;

import java.util.ArrayList;

public class BM25Model extends Model {

    /** Stub constructor  */
    public BM25Model() { super(); }

    @Override
    protected void scoreDocuments(String query) {
        ArrayList<Token> queryTokens = this.tokenizeQuery(query);

        double k = util.Settings.BM25_k; // TODO find good value for k
        double b = util.Settings.BM25_b; // TODO find good value for b
        for (Document d : this.documents.getDocuments()) {
            double score = 0.0;

            for (Token t : queryTokens) {
                Token indexToken = this.index.getToken(t);
                if (indexToken == null) { continue; } // token not in index
                double L_d = d.getLength();
                double L_ave = this.documents.getAverageLength();
                double tf = indexToken.getTF(d.getID());

                double idf_t = Math.log(this.documents.getN() / indexToken.getDF());
                double fraction  = (k + 1) / (k * (1 - b) + b * (L_d / L_ave) + tf);
                score += idf_t * fraction;
            }
            documents.setScore(d.getID(), score);
        }
    }
}
