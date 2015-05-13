package net.lapusiki.core;

import net.lapusiki.core.model.Predicate;

/**
 * Created by blvp on 12.05.15.
 */
public interface PredicateService {
    Predicate resolvePredicate(String... words);
}
