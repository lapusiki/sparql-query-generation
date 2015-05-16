package net.lapusiki.core.parser.impl;

import net.lapusiki.core.PredicateType;
import net.lapusiki.core.impl.MapQuestionService;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.QuestionService;
import net.lapusiki.core.QuestionType;
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

    public Pair<Question, String> parse(String sentence) throws Exception {

        Pair<Question, String> pair = new Pair<>();
        String[] parsedQuestion = new PrepositionsAndPunctuationParser().parse(sentence);

        QuestionType questionType = questionService.resolveQuestion(parsedQuestion[0]);

        // Если такой вопрос не найден в базе знаний, то выкидываем ошибку
        if (questionType == null) {
            throw new Exception("Вопрос отсутсвует в базе знаний");
        }

        pair.setFirst(new Question(questionType));

        // Если вопрос типа GET_FULLNAME_QUESTION
        if (questionType.equals(QuestionType.GET_FULLNAME_QUESTION)) {

            // Добавляем в question предикат для поиска людей по имени (foaf:full_name)
            pair.getFirst().setPredicate(new Predicate(PredicateType.NAME));

            // Запоминаем остаточную часть предложения начиная со 2 элемента
            pair.setSecond(wordsToSentence(Arrays.copyOfRange(parsedQuestion, 1, parsedQuestion.length)));

        // Если вопрос типа GET_COUNT_QUESTION
        } else if (questionType.equals(QuestionType.GET_VAR_QUESTION) ||
                questionType.equals(QuestionType.GET_COUNT_QUESTION)) {

            // Для того, чтобы определить объект, кол-во которого нужно найти,
            // пытаемся найти подходящий предикат который идет сразу после вопроса.
            // Далее сохраним найденный предикат в объекте вопроса

            Pair<Predicate, String> predicatePair = predicateParser.parse(wordsToSentence(Arrays.copyOfRange(parsedQuestion, 1, parsedQuestion.length)));

            if (predicatePair.getFirst() == null || predicatePair.getFirst().getPredicateType() == null) {
                throw new Exception("Не найден предикат для вопросительного слова типа " + questionType.getDescription());
            }

            pair.getFirst().setPredicate(predicatePair.getFirst());
            pair.setSecond(predicatePair.getSecond());

        } else {
            throw new Exception("Пока не умею обрабатывать такие вопросные слова");
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
