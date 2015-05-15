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

        // Кол-во слов находящихся в предикате
        // Используется для того, чтобы определить кол-во слов после предиката
        int predicateLength = 1;

        Predicate predicate = predicateService.resolvePredicate(parsedSentence[0]);

        // Если предикат не найден, то пробуем найти предикат по двум первым словам
        if (predicate == null || predicate.getValue() == null) {
            predicate = predicateService.resolvePredicate(parsedSentence[0], parsedSentence[1]);
            // Если предикат найден и его длина = 2
            if (predicate != null && predicate.getValue() != null) {
                predicateLength = 2;
            }
        }

        pair.setObject1(predicate);

        // Собираем остаточную часть после предиката,
        // Учитывая при этом длину предиката
        StringBuilder object2 = new StringBuilder();
        for (int i = predicateLength; i < parsedSentence.length; i++) {
            object2.append(parsedSentence[i]).append(" ");
        }
        pair.setObject2(object2.toString());

        return pair;
    }

}
