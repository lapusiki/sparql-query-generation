package net.lapusiki.core.impl;

import net.lapusiki.core.PredicateService;
import net.lapusiki.core.model.Predicate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 */
public class MapPredicateService implements PredicateService {

    private Map<String, String> predicates = new HashMap<String, String>(){{
       this.put("знает", "foaf:interest");
        this.put("любят", "foaf:interest");
    }};

    @Override
    public Predicate resolvePredicate(String... words) {
        if(words.length > 1) throw new UnsupportedOperationException("Пока не умею много слов");
        return new Predicate(predicates.get(words[0]));
    }
}
