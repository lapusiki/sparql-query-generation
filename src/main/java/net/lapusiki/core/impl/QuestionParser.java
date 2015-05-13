package net.lapusiki.core.impl;

import com.google.common.collect.Lists;
import net.lapusiki.core.Parser;
import net.lapusiki.core.QuestionService;
import net.lapusiki.core.QuestionType;
import net.lapusiki.core.model.Holder;
import net.lapusiki.core.model.Question;

import java.util.List;

/**
 * Created by blvp on 12.05.15.
 */
public class QuestionParser implements Parser {

    public Holder<Question, String> parse(String sentence) throws Exception {

        Holder<Question, String> holder = new Holder<>();
        String[] parsedQuestion = new PrepositionsParser().parse(sentence);

        QuestionService questionService = new MapQuestionService();
        QuestionType questionType = questionService.resolveQuestion(parsedQuestion[0]);
        holder.setObject1(new Question(questionType));

        // Если вопросы типа "кто?"
        if (questionType.equals(QuestionType.SIMPLE_QUESTION)) {
            // Собираем остаточную часть начиная со 2 элемента
            String object2 = "";
            for (int i = 1; i < parsedQuestion.length; i++) {
                object2 += new StringBuilder().append(parsedQuestion[i]).append(" ");
            }
            holder.setObject2(object2);
        }

        // Если вопросы типа "сколько?"
        else if (questionType.equals(QuestionType.CUSTOM_QUESTION)) {
            // Собираем остаточную часть начиная со 2 элемента
            String object2 = "";
            for (int i = 2; i < parsedQuestion.length; i++) {
                object2 += new StringBuilder().append(parsedQuestion[i]).append(" ");
            }
            holder.setObject2(object2);
        }

        return holder;

    }
}
