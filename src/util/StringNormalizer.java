package util;

import org.tartarus.snowball.EnglishSnowballStemmerFactory;
import org.tartarus.snowball.util.StemmerException;

/**
 * Applies all normalization methods to a given string. That is :
 *  + Converting it to lower case
 *  + Stemming
 *  + TODO Named entity recognition. (?)
 */
public class StringNormalizer {
	public static String normalize(String input) {
        String normalized = input.toLowerCase();
        try {
            normalized = EnglishSnowballStemmerFactory.getInstance().process(normalized);
        } catch (StemmerException e) {
            e.printStackTrace();
        }
        return normalized;
	}
}
