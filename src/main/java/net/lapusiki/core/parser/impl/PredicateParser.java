package net.lapusiki.core.parser.impl;

import net.lapusiki.core.PredicateService;
import net.lapusiki.core.impl.MapPredicateService;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kiv1n on 12.05.15.
 */
@Component
public class PredicateParser implements Parser {

    @Autowired
    private PredicateService predicateService;

    @Autowired
    private PrepositionsAndPunctuationParser prepositionsAndPunctuationParser;

    @Override
    public Pair<Predicate, String> parse(String sentence) throws Exception {

        Pair<Predicate, String> pair = new Pair<>();
        String[] parsedSentence = prepositionsAndPunctuationParser.parse(sentence);

        Predicate predicate = predicateService.resolvePredicate(parsedSentence);

        pair.setFirst(predicate);

        // Собираем остаточную часть после предиката,
        // Учитывая при этом длину предиката
        StringBuilder builder = new StringBuilder();
        for (int i = predicate.getPredicateLength(); i < parsedSentence.length; i++) {
            builder.append(parsedSentence[i]).append(" ");
        }
        pair.setSecond(builder.toString());

        return pair;
    }

}
