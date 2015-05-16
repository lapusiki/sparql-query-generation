package net.lapusiki.core.parser.impl;

import net.lapusiki.core.model.*;
import net.lapusiki.core.parser.Parser;

import java.util.Arrays;

/**
 * Created by kiv1n on 12.05.15.
 */
public class EntityParser implements Parser {

    @Override
    public Triple<Entity, String, OperatorType> parse(String sentence) throws Exception {

        Triple<Entity, String, OperatorType> triple = new Triple<>();
        String[] parsedSentence = new PrepositionsAndPunctuationParser().parse(sentence);

        // Пробуем проверить входяшее предложение на наличие предикатов в самом начале,
        // Это необходимо для предложений типа "знает студент Вася",
        // где подряд находится два предиката. Если предикат в начале предложения
        // найден, то вовзращаем пустой объект (entity)
        try {
            PredicateParser predicateParser = new PredicateParser();
            Pair<Predicate, String> predicatePair = predicateParser.parse(sentence);
            if (predicatePair.getObject1() != null && predicatePair.getObject1().getPredicateType() != null) {
                return new Triple<>(null, sentence, null);
            }
        } catch (Exception e) {
        }


        // Выделяем слова, которые относятся к объекту (entity)
        // Границей для объекта будут стоп слова типа "и", "или"
        for (int i = 0; i < parsedSentence.length; i++) {
            // Если найдено стоп слова "и"
            if (parsedSentence[i].equals(OperatorType.AND.getDescription())) {
                triple.setObject1(new Entity(wordsToSentence(Arrays.copyOfRange(parsedSentence, 0, i))));
                triple.setObject2(wordsToSentence(Arrays.copyOfRange(parsedSentence, i + 1, parsedSentence.length)));
                triple.setObject3(OperatorType.AND);
                break;
            // Если найдено стоп слово "или"
            } else if (parsedSentence[i].equals(OperatorType.OR.getDescription())) {
                triple.setObject1(new Entity(wordsToSentence(Arrays.copyOfRange(parsedSentence, 0, i))));
                triple.setObject2(wordsToSentence(Arrays.copyOfRange(parsedSentence, i + 1, parsedSentence.length)));
                triple.setObject3(OperatorType.OR);
                break;
            }
        }

        // Если стоп слова не найдеы, то добавляем все слова в объект
        // Получаем объект pair, у которого остаточная часть = null
        if (triple.getObject1() == null) {
            triple.setObject1(new Entity(sentence));
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
