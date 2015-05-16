package net.lapusiki.core;

import com.google.common.base.Joiner;
import net.lapusiki.core.Engine;
import net.lapusiki.core.model.*;
import net.lapusiki.core.model.enums.OperatorType;
import net.lapusiki.core.parser.impl.EntityParser;
import net.lapusiki.core.parser.impl.PredicateParser;
import net.lapusiki.core.parser.impl.PrepositionsAndPunctuationParser;
import net.lapusiki.core.parser.impl.QuestionParser;
import net.lapusiki.core.util.Pair;
import net.lapusiki.core.util.QueryHolder;
import net.lapusiki.core.util.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by kiv1n on 13.05.2015
 */
@Component
public class EngineV1 implements Engine {

    @Autowired
    private PrepositionsAndPunctuationParser prepositionsAndPunctuationParser;
    @Autowired
    private QuestionParser questionParser;
    @Autowired
    private PredicateParser predicateParser;
    @Autowired
    private EntityParser entityParser;

    @Override
    public QueryHolder processQuery(String sentence) throws Exception {

        QueryHolder queryHolder = new QueryHolder();

        // Избавляемся от предлогов и знаков пунктуации
        String[] wordsWithoutPrepositions = prepositionsAndPunctuationParser.parse(sentence);

        // Ищем вопросительное слово
        Pair<Question, String> pairAfterQuestionParser =
                questionParser.parse(Joiner.on(" ").join(wordsWithoutPrepositions));
        queryHolder.setQuestion(pairAfterQuestionParser.getFirst());

        // Рекурсивно ищем пары <Predicate, Entity>
        // Каждый шаг смотрим на остаточную часть, если она != null,
        // то запускаем следующий шаг
        Pair<Pair<Predicate, Entity>, String> pair =
                searchPredicateEntityPairs(pairAfterQuestionParser.getSecond());
        queryHolder.getPredicateEntityPairs().add(pair.getFirst());
        while (pair.getSecond() != null) {
            pair = searchPredicateEntityPairs(pair.getSecond());
            queryHolder.getPredicateEntityPairs().add(pair.getFirst());
        }

        // Удаляем все null в листе PredicateEntityPairs
        queryHolder.getPredicateEntityPairs().removeAll(Collections.singleton(null));

        return queryHolder;
    }

    private Pair<Pair<Predicate, Entity>, String> searchPredicateEntityPairs(String sentence) throws Exception {
        // Ищем предикат, далее в остальной части предложения ищем объект,
        // который относится к данному предикату.
        // После этого у нас остается необработанная часть предложения,
        // в котором будет уже другой предикат, но это уже совсем другая история
        Pair<Predicate, String> predicatePair = predicateParser.parse(sentence);
        Triple<Entity, String, OperatorType> entityTriple = entityParser.parse(predicatePair.getSecond());

        // Если entity для предиката не найден, то возвращаем пару = null
        if (entityTriple.getFirst() == null) {
            return new Pair<>(null, entityTriple.getSecond());
        }

        // Если entity parser обнаружил стоп слово, то добавляем соответсвующий оператор
        // в предикат
        if (entityTriple.getThird() != null) {
            predicatePair.getFirst().setAfterPredicateOperator(entityTriple.getThird());
        }

        return new Pair<>(new Pair<>(predicatePair.getFirst(), entityTriple.getFirst()), entityTriple.getSecond());
    }

}
