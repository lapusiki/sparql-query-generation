package net.lapusiki.core.parser.impl;

import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import net.lapusiki.core.model.*;
import net.lapusiki.core.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by kiv1n on 12.05.15.
 */
@Component
public class EntityParser implements Parser {

    @Autowired
    private PredicateParser predicateParser;

    @Autowired
    private PrepositionsAndPunctuationParser prepositionsAndPunctuationParser;

    @Override
    public Triple<Entity, String, OperatorType> parse(String sentence) throws Exception {

        Triple<Entity, String, OperatorType> triple = new Triple<>();
        String[] parsedSentence = prepositionsAndPunctuationParser.parse(sentence);

        // Пробуем проверить входяшее предложение на наличие предикатов в самом начале,
        // Это необходимо для предложений типа "знает студент Вася",
        // где подряд находится два предиката. Если предикат в начале предложения
        // найден, то вовзращаем пустой объект (entity)

        try {
            Pair<Predicate, String> predicatePair = predicateParser.parse(sentence);
            if (predicatePair.getFirst() != null && predicatePair.getFirst().getPredicateType() != null) {
                return new Triple<>(null, sentence, null);
            }
        } catch (Exception e) {
        }


        // Выделяем слова, которые относятся к объекту (entity)
        // Границей для объекта будут стоп слова типа "и", "или"
        for (int i = 0; i < parsedSentence.length; i++) {
            // Если найдено стоп слова "и"
            if (parsedSentence[i].equals(OperatorType.AND.getDescription())) {
                triple.setFirst(new Entity(wordsToSentence(Arrays.copyOfRange(parsedSentence, 0, i))));
                triple.setSecond(wordsToSentence(Arrays.copyOfRange(parsedSentence, i + 1, parsedSentence.length)));
                triple.setThird(OperatorType.AND);
                break;
            // Если найдено стоп слово "или"
            } else if (parsedSentence[i].equals(OperatorType.OR.getDescription())) {
                triple.setFirst(new Entity(wordsToSentence(Arrays.copyOfRange(parsedSentence, 0, i))));
                triple.setSecond(wordsToSentence(Arrays.copyOfRange(parsedSentence, i + 1, parsedSentence.length)));
                triple.setThird(OperatorType.OR);
                break;
            }
        }

        // Если стоп слова не найдеы, то добавляем все слова в объект
        // Получаем объект pair, у которого остаточная часть = null
        if (triple.getFirst() == null) {
            triple.setFirst(new Entity(sentence));
        }

        return triple;
    }

    private String wordsToSentence(String[] words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word).append(" ");
        }
        return builder.toString();
    }

}
