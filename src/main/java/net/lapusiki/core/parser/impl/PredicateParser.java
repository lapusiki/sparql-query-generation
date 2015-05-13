package net.lapusiki.core.parser.impl;

import net.lapusiki.core.impl.MapPredicateService;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.PredicateService;
import net.lapusiki.core.model.Predicate;

/**
 * Created by kiv1n on 12.05.15.
 */
public class PredicateParser implements Parser {

    @Override
    public Pair<Predicate, String> parse(String sentence) throws Exception {

        Pair<Predicate, String> pair = new Pair<>();
        String[] parsedSentence = new PrepositionsAndPunctuationParser().parse(sentence);

        PredicateService predicateService = new MapPredicateService();
        Predicate predicate = predicateService.resolvePredicate(parsedSentence[0]);

        pair.setObject1(predicate);

        // Собираем остаточную часть после предиката
        StringBuilder object2 = new StringBuilder();
        for (int i = 1; i < parsedSentence.length; i++) {
            object2.append(parsedSentence[i]).append(" ");
        }
        pair.setObject2(object2.toString());

        return pair;
    }

}
