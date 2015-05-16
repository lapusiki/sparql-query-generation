package net.lapusiki.core.util;

import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiv1n on 13.05.2015
 */
public class QueryHolder {

    private Question question;
    private List<Pair<Predicate, Entity>> predicateEntityPairs = new ArrayList<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Pair<Predicate, Entity>> getPredicateEntityPairs() {
        return predicateEntityPairs;
    }

    public void setPredicateEntityPairs(List<Pair<Predicate, Entity>> predicateEntityPairs) {
        this.predicateEntityPairs = predicateEntityPairs;
    }

    @Override
    public String toString() {
        return "QueryHolder{" +
                "question=" + question +
                ", predicateEntityPairs=" + predicateEntityPairs +
                '}';
    }
}
