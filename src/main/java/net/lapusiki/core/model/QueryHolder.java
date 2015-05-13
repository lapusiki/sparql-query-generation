package net.lapusiki.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiv1n on 13.05.2015
 */
public class QueryHolder {

    private Question question;
    private List<Pair<Predicate, Entity>> PredicateEntityPairs= new ArrayList<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Pair<Predicate, Entity>> getPredicateEntityPairs() {
        return PredicateEntityPairs;
    }

    public void setPredicateEntityPairs(List<Pair<Predicate, Entity>> predicateEntityPairs) {
        PredicateEntityPairs = predicateEntityPairs;
    }

    @Override
    public String toString() {
        return "QueryHolder{" +
                "question=" + question +
                ", PredicateEntityPairs=" + PredicateEntityPairs +
                '}';
    }
}