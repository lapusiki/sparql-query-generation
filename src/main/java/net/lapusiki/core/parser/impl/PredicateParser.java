package net.lapusiki.core.parser.impl;

import net.lapusiki.core.service.PredicateService;
import net.lapusiki.core.util.Pair;
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
        String second = buildRestSentence(parsedSentence, predicate);

        pair.setSecond(second);

        return pair;
    }

    private String buildRestSentence(String[] parsedSentence, Predicate predicate) {
        StringBuilder builder = new StringBuilder();
        Integer start = predicate.getPredicateLength();
        int end = parsedSentence.length;
        for (int i = start; i < end; i++) {
            builder.append(parsedSentence[i]).append(" ");
        }
        return builder.toString();
    }

}
