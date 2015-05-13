package net.lapusiki.core.impl;

import net.lapusiki.core.QuestionService;
import net.lapusiki.core.QuestionType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 */
public class MapQuestionService implements QuestionService {

    private Map<String, QuestionType> questions = new HashMap<String, QuestionType>() {{
        this.put("Кто", QuestionType.SIMPLE_QUESTION);
        this.put("Сколько", QuestionType.CUSTOM_QUESTION);
    }};


    @Override
    public QuestionType resolveQuestion(String... questionWords) {
        if (questionWords.length > 1)
            throw new UnsupportedOperationException("Пока не могу столько[" + questionWords.length + "] слов!");
        QuestionType questionType = questions.get(questionWords[0]);
        return (questionType == null ? QuestionType.SIMPLE_QUESTION : questionType);
    }

    @Override
    public String getSelectOption(QuestionType questionType, String variableName) {
        String result = "";
        switch (questionType) {
            case SIMPLE_QUESTION:
                result = variableName;
                break;
            case CUSTOM_QUESTION:
                result = "count(" + variableName + ")";
        }
        return " " + result + " ";
    }

}
