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


        // Кол-во слов находящихся в предикате, по умолчанию = 1
        // Используется для того, чтобы определить кол-во слов после предиката
        int predicateLength = 1;

        Predicate predicate = predicateService.resolvePredicate(parsedSentence[0]);

        // Если предикат не найден, то пробуем найти предикат по двум первым словам
        if (parsedSentence.length > 1 && (predicate == null || predicate.getPredicateType() == null)) {
            predicate = predicateService.resolvePredicate(parsedSentence[0], parsedSentence[1]);
            // Если предикат найден и его длина = 2
            if (predicate != null && predicate.getPredicateType() != null) {
                predicateLength = 2;
            }
        }

        // Если предикат по прежнему не найден, то выкидываем ошибку
        if (predicate == null || predicate.getPredicateType() == null) {
            throw new Exception(String.format("Предикат для фразы \"%s\" не найден. " +
                    "Он или больше 2 слов или отсутсвует в базе знаний", sentence));
        }

        pair.setFirst(predicate);

        // Собираем остаточную часть после предиката,
        // Учитывая при этом длину предиката
        StringBuilder object2 = new StringBuilder();
        for (int i = predicateLength; i < parsedSentence.length; i++) {
            object2.append(parsedSentence[i]).append(" ");
        }
        pair.setSecond(object2.toString());

        return pair;
    }

}
