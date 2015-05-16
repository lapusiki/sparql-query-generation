package net.lapusiki.core.parser.impl;

import net.lapusiki.core.PredicateType;
import net.lapusiki.core.impl.MapQuestionService;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.QuestionService;
import net.lapusiki.core.QuestionType;
import net.lapusiki.core.model.Question;

import java.util.Arrays;

/**
 * Created by blvp on 12.05.15.
 *
 * @author kiv1n
 */
public class QuestionParser implements Parser {

    public Pair<Question, String> parse(String sentence) throws Exception {

        Pair<Question, String> pair = new Pair<>();
        String[] parsedQuestion = new PrepositionsAndPunctuationParser().parse(sentence);

        QuestionService questionService = new MapQuestionService();
        QuestionType questionType = questionService.resolveQuestion(parsedQuestion[0]);

        // Если такой вопрос не найден в базе знаний, то выкидываем ошибку
        if (questionType == null) {
            throw new Exception("Вопрос отсутсвует в базе знаний");
        }

        pair.setObject1(new Question(questionType));

        // Если вопрос типа GET_FULLNAME_QUESTION
        if (questionType.equals(QuestionType.GET_FULLNAME_QUESTION)) {

            // Добавляем в question предикат для поиска людей по имени (foaf:full_name)
            pair.getObject1().setPredicate(new Predicate(PredicateType.FULL_NAME));

            // Запоминаем остаточную часть предложения начиная со 2 элемента
            pair.setObject2(wordsToSentence(Arrays.copyOfRange(parsedQuestion, 1, parsedQuestion.length)));

        // Если вопрос типа GET_COUNT_QUESTION
        } else if (questionType.equals(QuestionType.GET_VAR_QUESTION) ||
                questionType.equals(QuestionType.GET_COUNT_QUESTION)) {

            // Для того, чтобы определить объект, кол-во которого нужно найти,
            // пытаемся найти подходящий предикат который идет сразу после вопроса.
            // Далее сохраним найденный предикат в объекте вопроса
            PredicateParser parser = new PredicateParser();
            Pair<Predicate, String> predicatePair = parser.parse(wordsToSentence(Arrays.copyOfRange(parsedQuestion, 1, parsedQuestion.length)));

            if (predicatePair.getObject1() == null || predicatePair.getObject1().getPredicateType() == null) {
                throw new Exception("Не найден предикат для вопросительного слова типа " + questionType.getDescription());
            }

            pair.getObject1().setPredicate(predicatePair.getObject1());
            pair.setObject2(predicatePair.getObject2());

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
