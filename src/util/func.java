package util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class func {

    /**
     * Returns an array of files that are contained in a given folder.
     * @param root The folder to look into.
     * @return an array of files that are contained in a given folder.
     */
    public static File[] getDocumentCollection(String root) {
        ArrayList<File> ret = new ArrayList<File>();

        File dir = new File(root);
        File[] files = dir.listFiles(
                new FilenameFilter() {
                    public boolean accept(File dir, String filename) {
                        return filename.endsWith(".txt");
                    }
                });
        return files;
    }
}
