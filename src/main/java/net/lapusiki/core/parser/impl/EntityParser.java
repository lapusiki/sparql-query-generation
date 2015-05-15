package net.lapusiki.core.parser.impl;

import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kiv1n on 12.05.15.
 */
public class EntityParser implements Parser {

    public List<String> stopWords = new ArrayList<String>() {{
        add("и");
        add("или");
    }};

    @Override
    public Pair<Entity, String> parse(String sentence) throws Exception {

        // TODO: реализовать использованиее стоп слов
        // TODO: реализовать разделение на остаточную часть после объекта

        Pair<Entity, String> pair = new Pair<>();
        String[] parsedSentence = new PrepositionsAndPunctuationParser().parse(sentence);

        // Выделяем слова, которые относятся к объекту (entity)
        // Границей для объекта будут стоп слова типа "и", "или"
        for (String stopWord : stopWords) {
            for (int i = 0; i < parsedSentence.length; i++) {
                if (parsedSentence[i].equals(stopWord)) {
                    pair.setObject1(new Entity(wordsToSentence(Arrays.copyOfRange(parsedSentence, 0, i))));
                    pair.setObject2(wordsToSentence(Arrays.copyOfRange(parsedSentence, i+1, parsedSentence.length)));
                }
            }
        }

        // Если стоп слова не найдеы, то добавляем все слова в объект
        // Получаем объект pair, у которого остаточная часть = null
        if (pair.getObject1() == null) {
            pair.setObject1(new Entity(sentence));
        }

        return pair;
    }

    private String wordsToSentence(String[] words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word).append(" ");
        }
        return builder.toString();
    }

}
