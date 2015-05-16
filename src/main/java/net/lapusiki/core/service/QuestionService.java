package net.lapusiki.core.service;

import net.lapusiki.core.model.enums.QuestionType;

/**
 * Created by blvp on 12.05.15.
 */
public interface QuestionService {

    QuestionType resolveQuestion(String... questionWords);

}
