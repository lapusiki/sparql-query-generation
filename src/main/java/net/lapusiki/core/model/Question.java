package net.lapusiki.core.model;

import net.lapusiki.core.model.enums.QuestionType;

/**
 */
public class Question {

    private QuestionType type;

    private Predicate predicate;

    public Question() {
    }

    public Question(QuestionType type) {
        this.type = type;
    }

    public Question(QuestionType type, Predicate predicate) {
        this.type = type;
        this.predicate = predicate;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public String toString() {
        return "Question{" +
                "type=" + type +
                ", predicate=" + predicate +
                '}';
    }
}
