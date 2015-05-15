package net.lapusiki.core.impl;

import net.lapusiki.core.PredicateService;
import net.lapusiki.core.PredicateType;
import net.lapusiki.core.model.Predicate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
public class MapPredicateService implements PredicateService {

    private Map<String, PredicateType> predicates = new HashMap<String, PredicateType>() {{
        this.put("знает", PredicateType.INTEREST);
        this.put("любят", PredicateType.INTEREST);
        this.put("умеет писать", PredicateType.PL);
        this.put("посещает лабу", PredicateType.ITIS_LAB);
        this.put("смотрел", PredicateType.INTEREST);
    }};

    @Override
    public Predicate resolvePredicate(String... words) {
        if (words.length == 1) {
            return new Predicate(predicates.get(words[0]));
        } else if (words.length == 2) {
            return new Predicate(predicates.get(words[0] + " " + words[1]));
        } else {
            throw new UnsupportedOperationException("Пока не умею много слов");
        }
    }
}
