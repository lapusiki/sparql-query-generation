package net.lapusiki.core.impl;

import com.google.common.collect.Lists;
import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import net.lapusiki.core.PredicateService;
import net.lapusiki.core.PredicateType;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.similiarity.StringSimilarityDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("stringSimilarity")
    private StringSimilarityDelegate stringSimilarity;

    private Map<String, List<String>> predicates = new HashMap<String, List<String>>() {{
        this.put(PredicateType.PL.getValue(), Arrays.asList("знает", "языков программирования"));
        this.put(PredicateType.INTEREST.getValue(), Arrays.asList("любит", "любит", "умеет писать", "смотрел"));
        this.put(PredicateType.ITIS_LAB.getValue(), Arrays.asList("учится", "посещает лабу"));
        this.put(PredicateType.NAME.getValue(), Arrays.asList("студентов", "студент"));

    }};

    @Override
    public Predicate resolvePredicate(String... words) {
        Pair<String, Integer> bestMatch = stringSimilarity.getBestMatch(predicates, words);
        return (bestMatch != null? new Predicate(PredicateType.fromString(bestMatch.getFirst()), bestMatch.getSecond()): null);
    }
}
