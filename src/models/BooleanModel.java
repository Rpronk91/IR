package models;

import shared.Token;

import java.util.ArrayList;

public class BooleanModel extends Model {

    /** Stub constructor  */
    public BooleanModel() {
        super();
    }

    @Override
    protected void scoreDocuments(ArrayList<Token> queryTokens) {
        for (Token queryToken : queryTokens) {
            Token indexToken = this.index.getToken(queryToken);
            for (String documentID : indexToken.getPostingList().keySet()) {
                this.documents.setScore(documentID, 1.0);
            }
        }
    }
}
