package net.lapusiki.core.impl;

import net.lapusiki.core.Engine;
import net.lapusiki.core.model.*;
import net.lapusiki.core.parser.impl.EntityParser;
import net.lapusiki.core.parser.impl.PredicateParser;
import net.lapusiki.core.parser.impl.PrepositionsAndPunctuationParser;
import net.lapusiki.core.parser.impl.QuestionParser;

/**
 * Created by kiv1n on 13.05.2015
 */
public class EngineV1 implements Engine {

    private static PrepositionsAndPunctuationParser prepositionsAndPunctuationParser
            = new PrepositionsAndPunctuationParser();
    private static QuestionParser questionParser = new QuestionParser();
    private static PredicateParser predicateParser = new PredicateParser();
    private static EntityParser entityParser = new EntityParser();

    @Override
    public QueryHolder processQuery(String sentence) throws Exception {

        QueryHolder queryHolder = new QueryHolder();

        // Избавляемся от предлогов и знаков пунктуации
        String[] wordsWithoutPrepositions = prepositionsAndPunctuationParser.parse(sentence);

        // Ищем вопросительное слово
        Pair<Question, String> pairAfterQuestionParser =
                questionParser.parse(wordsToSentence(wordsWithoutPrepositions));
        queryHolder.setQuestion(pairAfterQuestionParser.getObject1());

        // Рекурсивно ищем пары <Predicate, Entity>
        // Каждый шаг смотрим на остаточную часть, если она != null,
        // то запускаем следующий шаг
        Pair<Pair<Predicate, Entity>, String> pair =
                searchPredicateEntityPairs(pairAfterQuestionParser.getObject2());
        queryHolder.getPredicateEntityPairs().add(pair.getObject1());
        while (pair.getObject2() != null) {
            pair = searchPredicateEntityPairs(pair.getObject2());
        }

        return queryHolder;
    }

    private Pair<Pair<Predicate, Entity>, String> searchPredicateEntityPairs(String sentence) throws Exception {
        // Ищем предикат, далее в остальной части предложения ищем объект,
        // который относится к данному предикату.
        // После этого у нас остается необработанная часть предложения,
        // в котором будет уже другой предикат, но это уже совсем другая история
        Pair<Predicate, String> predicatePair = predicateParser.parse(sentence);
        Pair<Entity, String> entityPair = entityParser.parse(predicatePair.getObject2());
        return new Pair<>(new Pair<>(predicatePair.getObject1(), entityPair.getObject1()), entityPair.getObject2());
    }

    private String wordsToSentence(String[] words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word).append(" ");
        }
        return builder.toString();
    }

}
