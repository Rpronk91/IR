package SearchEngine;

import models.*;
import shared.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Creates sample runs of the evaluation models & outputs the results to evaluatorOutputs folder.
 */
public class Evaluator {

    /**
     * Returns a HashMap containing the test queries with their IDs.
     * @return a HashMap containing the test queries with their IDs.
     */
    private static HashMap<Integer, String> getTestQueries() {
        HashMap<Integer, String> ret = new HashMap<>();
        ret.put(6, "sustainable ecosystems");
        ret.put(7, "air guitar textile sensors");
        return ret;
    }

    /**
     * Returns a list with the available models to be evaluated.
     * @return a list with the available models to be evaluated.
     */
    private static List<Model> getModelsList() {
        ArrayList<Model> ret = new ArrayList<>();
        ret.add( new BooleanModel() );
        ret.add( new TFIDFModel() );
        ret.add( new BM25Model() );
        return ret;
    }

    private static void outputRanking(DocumentCollection dc, Model m, int qID) {
        String runID = m.toString();
        String fname = qID + "" + runID + ".eval";
        final String outputFolder = "evaluatorOutputs";

        File theDir = new File(outputFolder);
        if (!theDir.exists()) { // if the directory does not exist, create it
            boolean result = theDir.mkdir();
            if(result) { System.out.println("evaluatorOutputs folder created"); }
        }

        try {
            FileOutputStream fos = new FileOutputStream(outputFolder + "/" +fname);

            int rank = 1;
            for (Document d : dc.getDocuments()) {
                // <queryID> Q0 <documentID> <rank> <score> <runID>
                double score = d.getScore();
                String docID = d.getID();
                if (Double.isNaN(score)) { score = 0.0; }
                String line = qID + " "  + docID + " "  + rank + " " + score + " " + runID;
                fos.write( (line + "\n").getBytes() );
                rank++;
            }

            fos.close();
        } catch (IOException ex) { ex.printStackTrace(); }

    }

    public static void main(String[] args) {
        HashMap<Integer, String> q = getTestQueries();
        List<Model> models = getModelsList();

        for (int key : q.keySet()) {
            String query = q.get(key);

            for (Model m : models) {
                DocumentCollection ranking = m.getEvaluationRanking(query);
                outputRanking(ranking, m, key);
            }
        }
    }
}
