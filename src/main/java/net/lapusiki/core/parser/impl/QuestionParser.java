package net.lapusiki.core.parser.impl;

import net.lapusiki.core.impl.MapQuestionService;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.QuestionService;
import net.lapusiki.core.QuestionType;
import net.lapusiki.core.model.Question;

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
        pair.setObject1(new Question(questionType));

        // Если вопросы типа "кто?"
        if (questionType.equals(QuestionType.WHO_QUESTION)) {
            // Собираем остаточную часть предложения начиная со 2 элемента
            StringBuilder object2 = new StringBuilder();
            for (int i = 1; i < parsedQuestion.length; i++) {
                object2.append(parsedQuestion[i]).append(" ");
            }
            pair.setObject2(object2.toString());
        } else {
            throw new Exception("Пока не умею обрабатывать такие вопросные слова");
        }

        return pair;

    }
}
