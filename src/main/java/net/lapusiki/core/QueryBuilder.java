package net.lapusiki.core;

import net.lapusiki.core.impl.PersonVariableGenerator;
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

    private static VariableGenerator variableGenerator = new PersonVariableGenerator();

    private final String QUERY_TEMPLATE_FANCY = "SELECT %s \n" +
            "WHERE { \n " +
            "%s" +
            "%s " +
            "FILTER (%s)\n" +
            "}";

    private String rdfType;
    private Question question;
    private List<Pair<Predicate, Entity>> predicateEntityPairs = new ArrayList<>();

    public QueryBuilder() {
    }

    public QueryBuilder rdfType(String rdfType) {
        this.rdfType = rdfType;
        return this;
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
        String selectHeader = "";
        if (question.getType() == QuestionType.WHO_QUESTION) {
            selectHeader += String.format("%s", "?full_name");
        } else {
            throw new Exception("Пока не умею строить запросы для такого типа вопроса");
        }

        // Заполняем rdf type в части where
        String whereRdfType = String.format("?%s rdf:rdfType foaf:%s .\n", this.rdfType, this.rdfType);

        // Заполняем оставшуюся часть where
        // TODO: в часть where нужно ещё добавлять поля, которые используются в select
        StringBuilder restWherePart = new StringBuilder();
        for (Pair<Predicate, Entity> pair : predicateEntityPairs) {
            restWherePart.append(String.format(" ?person %s ?%s .\n", pair.getObject1().getValue(), pair.getObject1().hashCode()));
        }

        // Заполняем часть filters
        StringBuilder filters = new StringBuilder();
        for (Pair<Predicate, Entity> pair : predicateEntityPairs) {
            filters.append(String.format("str(%s) = \"%s\" && ", pair.getObject1().hashCode(), pair.getObject2().getValue()));
        }
        // TODO: избавить от этой вакханалии
        // Убираем последние символы " && "
        filters.delete(filters.length() - 4, filters.length());

        return String.format(QUERY_TEMPLATE_FANCY, selectHeader, whereRdfType, restWherePart, filters);
    }


}
