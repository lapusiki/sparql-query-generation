package net.lapusiki.core.impl;

import com.google.common.collect.Lists;
import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import net.lapusiki.core.PredicateService;
import net.lapusiki.core.PredicateType;
import net.lapusiki.core.model.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
@Component
public class MapPredicateService implements PredicateService {

    @Autowired
    private StringSimilarityInterface stringSimilarity;

    private Map<PredicateType, List<String>> predicates = new HashMap<PredicateType, List<String>>() {{
        this.put(PredicateType.PL, Arrays.asList("знает", "языков программирования"));
        this.put(PredicateType.INTEREST, Arrays.asList("любит", "любит", "умеет писать", "смотрел"));
        this.put(PredicateType.ITIS_LAB, Arrays.asList("учится", "посещает лабу"));
        this.put(PredicateType.NAME, Arrays.asList("студентов", "студент"));

    }};

    @Override
    public Predicate resolvePredicate(String... words) {
        StringBuilder builder = new StringBuilder();
        Integer countWords = 0;

        Double totalSim = Double.MIN_VALUE;
        PredicateType totalMaxPredicate = null;
        for (String word : words) {
            builder.append(word).append(" ");
            Double maxSimilarityBetweenPredicateTypes = Double.MIN_VALUE;
            PredicateType maxPredicateType = null;
            for (Map.Entry<PredicateType, List<String>> entry : predicates.entrySet()) {
                Double maxSimilarityForPredicateValues = Double.MIN_VALUE;
                for (String stringToCompare : entry.getValue()) {
                    double similarity = stringSimilarity.similarity(builder.toString(), stringToCompare);
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
        } else return new Predicate(totalMaxPredicate, countWords);
    }
}
