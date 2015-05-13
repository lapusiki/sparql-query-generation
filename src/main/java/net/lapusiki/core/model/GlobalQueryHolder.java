package net.lapusiki.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class GlobalQueryHolder {

    private List<Question> questions = new ArrayList<>();
    private List<Predicate> predicates = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
