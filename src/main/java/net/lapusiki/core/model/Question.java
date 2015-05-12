package net.lapusiki.core.model;

import net.lapusiki.core.QuestionType;

/**
 */
public class Question {

    private QuestionType type;

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "type=" + type +
                '}';
    }
}
