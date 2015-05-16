package net.lapusiki.core;

import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiv1n on 12.05.15.
 */
public class QueryBuilder {

    private final String QUERY_TEMPLATE_FANCY = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n" +
            "PREFIX : <http://cll.niimm.ksu.ru/swcourse/> \n" +
            "SELECT %s\n" +
            "WHERE {\n" +
            "%s" +
            " FILTER (%s)\n" +
            "}";

    private String rdfType;
    private Question question;
    private List<Pair<Predicate, Entity>> predicateEntityPairs = new ArrayList<>();

    public QueryBuilder() {
    }

    public QueryBuilder question(Question question) {
        this.question = question;
        return this;
    }

    public QueryBuilder predicateEntityPair(Pair<Predicate, Entity> pair) {
        predicateEntityPairs.add(pair);
        return this;
    }

    public QueryBuilder predicateEntityPair(List<Pair<Predicate, Entity>> pairs) {
        predicateEntityPairs.addAll(pairs);
        return this;
    }

    public String build() throws Exception {

        // Проверка на наличие всех полей
        if (question == null) {
            throw new Exception("Не найден question");
        }
        if (predicateEntityPairs.size() == 0) {
            throw new Exception("Не найдены пары <Predicate,Entity>");
        }

        // Заполняем select header
        StringBuilder selectHeader = new StringBuilder();
        if (question.getType() == QuestionType.GET_FULLNAME_QUESTION) {
            selectHeader.append(String.format("?%s", question.getPredicate().hashCode()));
        } else if (question.getType() == QuestionType.GET_COUNT_QUESTION) {
            selectHeader.append(String.format("count(?%s)", question.getPredicate().hashCode()));
        } else if (question.getType() == QuestionType.GET_VAR_QUESTION) {
            selectHeader.append(String.format("?%s", question.getPredicate().hashCode()));
        } else {
            throw new Exception("Пока не умею строить запросы для такого типа вопроса");
        }

        // Заполняем часть where
        StringBuilder wherePart = new StringBuilder();
        // Добавляем всё, что относится к select
        wherePart.append(String.format(" ?person %s ?%s .\n", question.getPredicate().getPredicateType().getValue(), question.getPredicate().hashCode()));
        // Добавляем всё, что относится к filter
        for (Pair<Predicate, Entity> pair : predicateEntityPairs) {
            wherePart.append(String.format(" ?person %s ?%s .\n", pair.getObject1().getPredicateType().getValue(), pair.getObject1().hashCode()));
        }

        // Заполняем часть filters
        StringBuilder filters = new StringBuilder();
        for (Pair<Predicate, Entity> pair : predicateEntityPairs) {
            filters.append(String.format("str(?%s) = \"%s\"", pair.getObject1().hashCode(), pair.getObject2().getValue()));
            // Опредялем, есть после придиката операторы "и" или "или"
            if (pair.getObject1().getAfterPredicateOperator() != null) {
                switch (pair.getObject1().getAfterPredicateOperator()) {
                    case AND:
                        filters.append(" && ");
                        break;
                    case OR:
                        filters.append(" || ");
                        break;
                }
            }
        }

        return String.format(QUERY_TEMPLATE_FANCY, selectHeader, wherePart, filters);
    }


}
