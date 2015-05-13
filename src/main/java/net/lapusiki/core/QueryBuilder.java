package net.lapusiki.core;

import net.lapusiki.core.impl.VariableGeneratorImpl;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.model.Question;

import java.util.List;

/**
 */
public class QueryBuilder {

    private static VariableGenerator variableGenerator = new VariableGeneratorImpl();

    private final String QUERY_TEMPLATE_FANCY = "SELECT %s \n" +
            "WHERE { \n " +
            "%s" +
            "%s " +
            "FILTER (%s)\n" +
            "}";

    private String type;
    private List<Question> questions;
    private List<Predicate> predicates;
    private List<Entity> entities;

    public QueryBuilder() {
    }

    public QueryBuilder type(String type) {
        this.type = type;
        return this;
    }

    public QueryBuilder questions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public QueryBuilder predicates(List<Predicate> predicates) {
        this.predicates = predicates;
        return this;
    }

    public QueryBuilder entities(List<Entity> entities) {
        this.entities = entities;
        return this;
    }

    public String build() {

        // Variables in select
        // TODO: use question types
        String selectOptions = "";
        for (int i = 0; i < predicates.size(); i++) {
            // If question type is custom then add "count()"
            if (questions.get(i).getType().equals(QuestionType.CUSTOM_QUESTION)) {
                selectOptions += String.format("count(%s)", variableGenerator.getVariable(predicates.get(i).getValue()));
            } else {
                selectOptions += String.format("%s", variableGenerator.getVariable(predicates.get(i).getValue()));
            }
        }
        for (Predicate predicate : predicates) {

        }

        // Rdf Type in Where
        String rdfType = String.format("?%s rdf:type foaf:%s .\n", type, type);

        // Part in where
        String wherePart = "";
        for (Predicate predicate : predicates) {
            wherePart += String.format(" ?%s %s %s .\n", type, predicate.getValue(),
                    variableGenerator.getVariable(predicate.getValue()));
        }

        // Filters
        String filters = "";
        for (int i = 0; i < predicates.size(); i++) {
            filters += String.format("str(%s) = \"%s\" && ",
                    variableGenerator.getVariable(predicates.get(i).getValue()), entities.get(i).getValue());
        }
        filters = filters.substring(0, filters.length() - 4);

        return String.format(QUERY_TEMPLATE_FANCY, selectOptions, rdfType, wherePart, filters);
    }


}
