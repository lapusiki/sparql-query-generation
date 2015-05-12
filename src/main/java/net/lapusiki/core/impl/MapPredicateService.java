package net.lapusiki.core.impl;

import net.lapusiki.core.PredicateService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 */
public class MapPredicateService implements PredicateService {

    private Map<String, String> predicates = new HashMap<String, String>(){{
       this.put("знает", "foaf:interest");
    }};

    @Override
    public String resolvePredicate(String... words) {
        if(words.length > 1) throw new UnsupportedOperationException("Пока не умею много слов");
        return predicates.get(words[0]);
    }
}
