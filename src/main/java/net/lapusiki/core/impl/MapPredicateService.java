package net.lapusiki.core.impl;

import net.lapusiki.core.PredicateService;
import net.lapusiki.core.PredicateType;
import net.lapusiki.core.model.Predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
public class MapPredicateService implements PredicateService {

    public final List<String> names = new ArrayList<String>() {{
        add("Вася");
    }};

    private Map<String, PredicateType> predicates = new HashMap<String, PredicateType>() {{
        this.put("знает", PredicateType.INTEREST);
        this.put("любят", PredicateType.INTEREST);
        this.put("умеет писать", PredicateType.PL);
        this.put("посещает лабу", PredicateType.ITIS_LAB);
        this.put("смотрел", PredicateType.INTEREST);
        this.put("студентов", PredicateType.FULL_NAME);
        this.put("учится", PredicateType.ITIS_LAB);
        this.put("Вася", PredicateType.FULL_NAME);

        // Добавляем имена как предикат типа FULL_NAME
        for (String name : names) {
            this.put(name, PredicateType.FULL_NAME);
        }
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
