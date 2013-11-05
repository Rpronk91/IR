package util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * All helping functions that don't really belong to a single class.
 */
public class func {

    /**
     * Returns an array of files that are contained in a given folder.
     * @param root The folder to look into.
     * @return an array of files that are contained in a given folder.
     */
    public static File[] getDocumentCollection(String root) {
        ArrayList<File> ret = new ArrayList<>();

        File dir = new File(root);
        File[] files = dir.listFiles(
                new FilenameFilter() {
                    public boolean accept(File dir, String filename) {
                        return filename.endsWith(".txt");
                    }
                });
        return files;
    }

    /**
     * Reads the collection statistics file and returns a container with the info acquired from it.
     * @return HashMap containing the collection statistics.
     */
    public static HashMap<String, Double> readDocumentStatistics() {
        HashMap<String, Double> ret = new HashMap<>();
        try {
            File f = new File(Settings.DOCUMENT_STATS_FNAME);
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                String[] spaceSplit = line.split(" ");
                String fileID = spaceSplit[0];
                double fileLength = Double.parseDouble(spaceSplit[1]);

                ret.put(fileID, fileLength);
            }
            fis.close();
            br.close();
        } catch (IOException ex) { ex.printStackTrace(); }
        return ret;
    }
}
