package net.lapusiki.core.impl;

import net.lapusiki.core.Parser;
import net.lapusiki.core.PredicateService;
import net.lapusiki.core.model.Holder;
import net.lapusiki.core.model.Predicate;

import java.util.Arrays;

/**
 */
public class PredicateParser implements Parser {

    @Override
    public Holder<Predicate, String> parse(String sentence) throws Exception {

        Holder<Predicate, String> holder = new Holder<>();
        String[] parsedSentence = new PrepositionsParser().parse(sentence);

        PredicateService predicateService = new MapPredicateService();
        Predicate predicate = predicateService.resolvePredicate(parsedSentence[0]);

        holder.setObject1(predicate);
        holder.setObject2(Arrays.toString(Arrays.copyOfRange(parsedSentence, 1, parsedSentence.length)));

        return holder;
    }

}
