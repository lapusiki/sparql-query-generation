package net.lapusiki.core.service.impl;

import com.google.common.collect.Lists;
import net.lapusiki.core.service.PredicateService;
import net.lapusiki.core.model.enums.PredicateType;
import net.lapusiki.core.util.Pair;
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
        this.put(PredicateType.PL.getValue(), Lists.asList("знает", "языков программирования", "знать, учить, изучать, программировать на, писать на, использовать, любить".split(", ")));
        this.put(PredicateType.ITIS_LAB.getValue(), Lists.asList("учится", "посещает лабу", "посещать лабу, учиться, стажироваться, практиковаться, проходить практику, проходить стажировку, числиться в, числиться за, посещать, выбирать, заниматься".split(", ")));
        this.put(PredicateType.NAME.getValue(), Arrays.asList("студентов", "студент", "зовут", "имя", "кличка", "величают"));
        this.put(PredicateType.INTEREST.getValue(), Lists.asList("умеет писать", "смотрел", "знать, любить, читать, слушать, уметь, посещать, смотреть, интересоваться, изучать, покупать, брать, писать, сочинять, снимать, увлекаться, помнить, выбирать, обсуждать, вдохновлять, интриговать, заинтриговать, иметь, интересы".split(", ")));
        this.put(PredicateType.MBOX.getValue(), Arrays.asList("почта, адрес, e-mail, электронная почта, мыло".split(", ")));
    }};

    @Override
    public Predicate resolvePredicate(String... words) {
        Pair<String, Integer> bestMatch = stringSimilarity.getBestMatch(predicates, words);
        return (bestMatch != null? new Predicate(PredicateType.fromString(bestMatch.getFirst()), bestMatch.getSecond()): null);
    }
}
