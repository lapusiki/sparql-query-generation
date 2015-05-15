package net.lapusiki.core.impl;

import net.lapusiki.core.QuestionService;
import net.lapusiki.core.QuestionType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
public class MapQuestionService implements QuestionService {

    private Map<String, QuestionType> questions = new HashMap<String, QuestionType>() {{
        this.put("Кто", QuestionType.WHO_QUESTION);
        this.put("Сколько", QuestionType.COUNT_QUESTION);
    }};


    @Override
    public QuestionType resolveQuestion(String... questionWords) {
        if (questionWords.length > 1) {
            throw new UnsupportedOperationException("Пока не могу столько[" + questionWords.length + "] слов!");
        }
        return questions.get(questionWords[0]);
    }

}
