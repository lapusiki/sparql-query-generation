package net.lapusiki.core;

/**
 * Created by blvp on 12.05.15.
 */
public interface QuestionService {

    QuestionType resolveQuestion(String... questionWords);

    String getSelectOption(QuestionType questionType, String variableName);
}
