package models;

import shared.Token;

import java.util.ArrayList;

public class BooleanModel extends Model {

    /** Stub constructor  */
    public BooleanModel() {
        super();
    }
    
    /**
     * Applies an AND operation on two arraylists
     * @return Returns a new arraylist which has the elements which are both in A and B.
     */
    private ArrayList<String> ANDoperator(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C = new ArrayList<String>();
        if(A.size() < B.size()) {  //check starting from smallest array
            for (String element : A) {
                if (B.contains(element))
                    C.add(element);
            }
        } else {
            for (String element : B) {
                if (A.contains(element))
                    C.add(element);
            }
        }
        return C;
    }
    
    /**
     * Applies an OR operation on two arraylists
     * @return Returns a new arraylist which has the elements which are in A or B without duplicates.
     */
    private ArrayList<String> ORoperator(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C;
        if(A.size() < B.size()) { //check starting from biggest array
            C = new ArrayList<String>(B);
            for (String element : A) {
                if (!B.contains(element))
                    C.add(element);
            }
        } else {
            C = new ArrayList<String>(A);
            for (String element : B) {
                if (!A.contains(element))
                    C.add(element);
            }
        }
        return C;
    }
    
    @Override
    protected void scoreDocuments(String query) {
        ArrayList<String> MD = null; //Matching Documents
        ArrayList<String> temp = null;
        ArrayList<Token> q = super.tokenizeQuery(query); // tokenize query
        String StringToken;
        String operator = "";

        //Parse tokens
        for (Token queryToken : q) {
            StringToken = queryToken.getNormalizedString();
            if (StringToken.equals("and") || StringToken.equals("or") || StringToken.equals("not")) {
                operator = StringToken;
            } else {
                Token indexToken = this.index.getToken(queryToken);
                //For the first argument
                if (MD == null)
                    //If the first PostingList is empty, start with an empty list
                    if (!(indexToken == null))
                        MD = new ArrayList<String>(indexToken.getPostingList().keySet());
                    else
                        MD = new ArrayList<String>();
                else {
                    //If the next PostingList is empty, temp will be an empty list
                    if (!(indexToken == null))
                        temp = new ArrayList<String>(indexToken.getPostingList().keySet());
                    else
                        temp = new ArrayList<String>();
                    if(operator.equals("or"))
                        MD = ORoperator(MD, temp);
                    else //if(operator == "and")  (The base case)
                        MD = ANDoperator(MD, temp);
                }
            }
        }
        for (String documentID : MD) {
            this.returnSet.setScore(documentID, 1.0);
        }
    }
    
    
}
