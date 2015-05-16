package net.lapusiki.core.parser.impl;

import com.google.common.base.Joiner;
import net.lapusiki.core.model.*;
import net.lapusiki.core.model.enums.OperatorType;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.service.EntityService;
import net.lapusiki.core.service.impl.CSVEntityService;
import net.lapusiki.core.service.impl.MapEntityService;
import net.lapusiki.core.util.Pair;
import net.lapusiki.core.util.Triple;
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

    @Autowired
    private CSVEntityService entityService;

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
            boolean isPredicateFound = predicatePair.getFirst() != null && predicatePair.getFirst().getPredicateType() != null;
            if (isPredicateFound) {
                return new Triple<>(null, sentence, null);
            }
        } catch (Exception e) {
        }


        // Выделяем слова, которые относятся к объекту (entity)
        // Границей для объекта будут стоп слова типа "и", "или"
        for (int i = 0; i < parsedSentence.length; i++) {
            // Если найдено стоп слова "и"
            OperatorType operatorType = null;
            if (parsedSentence[i].equals(OperatorType.AND.getDescription())) {
                operatorType = OperatorType.AND;
            // Если найдено стоп слово "или"
            } else if (parsedSentence[i].equals(OperatorType.OR.getDescription())) {
                operatorType = OperatorType.OR;
            }

            if (operatorType != null) {
                triple.setFirst(new Entity(Joiner.on(" ").join(Arrays.copyOfRange(parsedSentence, 0, i))));
                triple.setSecond(Joiner.on(" ").join(Arrays.copyOfRange(parsedSentence, i + 1, parsedSentence.length)));
                triple.setThird(operatorType);
                break;
            }

        }
        // Если стоп слова не найдеы, то добавляем все слова в объект
        // Получаем объект tripple, у которого остаточная часть = null
        if (triple.getFirst() == null) {
            triple.setFirst(new Entity(sentence));
        }

        // Заменяем полученный entity на подходящий из базы данных sparql
        Entity entity = entityService.resolveEntity(prepositionsAndPunctuationParser.parse(triple.getFirst().getValue()));
        if (entity != null) {
            triple.setFirst(entity);
        }

        return triple;
    }

}
