package net.lapusiki.core.parser.impl;

import com.google.common.base.Joiner;
import net.lapusiki.core.model.enums.PredicateType;
import net.lapusiki.core.util.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.service.QuestionService;
import net.lapusiki.core.model.enums.QuestionType;
import net.lapusiki.core.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by blvp on 12.05.15.
 *
 * @author kiv1n
 */
@Component
public class QuestionParser implements Parser {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PredicateParser predicateParser;

    @Autowired
    private PrepositionsAndPunctuationParser prepositionsAndPunctuationParser;

    public Pair<Question, String> parse(String sentence) throws Exception {

        Pair<Question, String> pair = new Pair<>();
        String[] parsedQuestion = prepositionsAndPunctuationParser.parse(sentence);

        QuestionType questionType = questionService.resolveQuestion(parsedQuestion[0]);

        // Если такой вопрос не найден в базе знаний, то выкидываем ошибку
        if (questionType == null) {
            throw new Exception("Вопрос отсутсвует в базе знаний");
        }

        pair.setFirst(new Question(questionType));

        // Если вопрос типа GET_NAME_QUESTION
        switch(questionType){
            case GET_NAME_QUESTION:
                // Добавляем в question предикат для поиска людей по имени (foaf:full_name)
                pair.getFirst().setPredicate(new Predicate(PredicateType.NAME));

                // Запоминаем остаточную часть предложения начиная со 2 элемента
                String restString = Joiner.on(" ").join(Arrays.copyOfRange(parsedQuestion, 1, parsedQuestion.length));
                pair.setSecond(restString);
                break;
            case GET_VAR_QUESTION:
            case GET_COUNT_QUESTION:

                // Для того, чтобы определить объект, кол-во которого нужно найти,
                // пытаемся найти подходящий предикат который идет сразу после вопроса.
                // Далее сохраним найденный предикат в объекте вопроса

                String restSentence = Joiner.on(" ").join(Arrays.copyOfRange(parsedQuestion, 1, parsedQuestion.length));
                Pair<Predicate, String> predicatePair = predicateParser.parse(restSentence);

                if (predicatePair.getFirst() == null || predicatePair.getFirst().getPredicateType() == null) {
                    throw new Exception("Не найден предикат для вопросительного слова типа " + questionType.getDescription());
                }

                pair.getFirst().setPredicate(predicatePair.getFirst());
                pair.setSecond(predicatePair.getSecond());
                break;
            default: throw new IllegalArgumentException("неизвестный тип запроса");
        }
        return pair;

    }

}
