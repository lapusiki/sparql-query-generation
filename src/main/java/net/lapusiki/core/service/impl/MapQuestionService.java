package net.lapusiki.core.service.impl;

import net.lapusiki.core.service.QuestionService;
import net.lapusiki.core.model.enums.QuestionType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
@Component
public class MapQuestionService implements QuestionService {

    private Map<String, QuestionType> questions = new HashMap<String, QuestionType>() {{
        this.put("Кто", QuestionType.GET_FULLNAME_QUESTION);
        this.put("Где", QuestionType.GET_VAR_QUESTION);
        this.put("Сколько", QuestionType.GET_COUNT_QUESTION);
    }};


    @Override
    public QuestionType resolveQuestion(String... questionWords) {
        if (questionWords.length > 1) {
            throw new UnsupportedOperationException("Пока не могу столько[" + questionWords.length + "] слов!");
        }
        return questions.get(questionWords[0]);
    }

}
