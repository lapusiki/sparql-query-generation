package net.lapusiki.core.similiarity;

import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import net.lapusiki.core.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by blvp on 16.05.15.
 */
public interface StringSimilarityDelegate extends StringSimilarityInterface {
    /**
     * returns the best match of words sequence in order to find better solution for given words
     * @param map dictionary
     * @param words word sequence
     * @return Pair of found key from dictionary and count of words with better solution
     */
    Pair<String, Integer> getBestMatch(Map<String, List<String>> map, String... words);
}
