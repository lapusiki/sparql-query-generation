package net.lapusiki.core.similiarity.impl;

import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.similiarity.StringSimilarityDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by blvp on 16.05.15.
 */
@Component("stringSimilarity")
public class StringSimilarityDelegateImpl implements StringSimilarityDelegate {

    @Autowired
    @Qualifier("stringSimilarityDelegate")
    private StringSimilarityInterface delegate;

    @Override
    public double similarity(String s, String s1) {
        return delegate.similarity(s, s1);
    }

    @Override
    public double distance(String s, String s1) {
        return delegate.similarity(s, s1);
    }

    @Override
    public Pair<String, Integer> getBestMatch(Map<String, List<String>> map, String... words) {

        StringBuilder builder = new StringBuilder();
        Integer countWords = 0;

        Double totalSim = Double.MIN_VALUE;
        String totalMaxPredicate = null;
        for (String word : words) {
            builder.append(word).append(" ");
            Double maxSimilarityBetweenPredicateTypes = Double.MIN_VALUE;
            String maxPredicateType = null;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                Double maxSimilarityForPredicateValues = Double.MIN_VALUE;
                for (String stringToCompare : entry.getValue()) {
                    double similarity = delegate.similarity(builder.toString(), stringToCompare);
                    if(similarity > maxSimilarityForPredicateValues) {
                        maxSimilarityForPredicateValues = similarity;
                    }
                }
                if(maxSimilarityBetweenPredicateTypes < maxSimilarityForPredicateValues){
                    maxSimilarityBetweenPredicateTypes = maxSimilarityForPredicateValues;
                    maxPredicateType = entry.getKey();
                }
            }

            boolean foundBetterSolution = totalSim < maxSimilarityBetweenPredicateTypes;

            if(foundBetterSolution){
                totalSim = maxSimilarityBetweenPredicateTypes;
                totalMaxPredicate = maxPredicateType;
                countWords++;
            } else {
                break;
            }
        }
        if(totalSim < 0.4){
            return null;
        } else return new Pair<>(totalMaxPredicate, countWords);

    }
}
