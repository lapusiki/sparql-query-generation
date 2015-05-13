package net.lapusiki.core.parser.impl;

import net.lapusiki.core.impl.MapPredicateService;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.PredicateService;
import net.lapusiki.core.model.Holder;
import net.lapusiki.core.model.Predicate;

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

        // Собираем остаточную часть после предиката
        String object2 = "";
        for (int i = 1; i < parsedSentence.length; i++) {
            object2 += new StringBuilder().append(parsedSentence[i]).append(" ");
        }
        holder.setObject2(object2);

        return holder;
    }

}
