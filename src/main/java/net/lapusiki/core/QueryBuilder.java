package net.lapusiki.core;

import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Predicate;

/**
 */
public class QueryBuilder {

    private final String QUERY_TEMPLATE_FANCY = "SELECT %s \n" +
            "WHERE { \n " +
            "%s" +
            "%s " +
            "FILTER (%s)\n" +
            "}";

    private String type;
    private Predicate predicate;
    private Entity entity;
    private boolean fancy = false;

    public QueryBuilder() {
    }

    public QueryBuilder type(String type) {
        this.type = type;
        return this;
    }

    public QueryBuilder predicate(Predicate predicate) {
        this.predicate = predicate;
        return this;
    }

    public QueryBuilder entity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public QueryBuilder fancy(boolean fancy) {
        this.fancy = fancy;
        return this;
    }

    public String build() {

        // Variables in select
        String selectVariables = "";
        for (String predicate : this.predicate.getValues()) {
            selectVariables += "?" + type + "_" + predicate + " ";
        }

        // Rdf Type in Where
        String rdfType = "?" + type + " rdf:type foaf:" + type + " .\n";

        // Predicates
        String predicates = "";
        for (String predicate : this.predicate.getValues()) {
            predicates += " ?" + type + " foaf:" + predicate + " ?" + type + "_" + predicate + " ." + "\n";
        }

        // Filters
        String filters = "";
        for (int i = 0; i < this.entity.getValues().size(); i++) {
            filters += "str(?" + type + "_" + predicate.getValues().get(i)
                    + ") = \"" + this.entity.getValues().get(i) + "\"" + " && ";
        }
        filters = filters.substring(0, filters.length() - 4);


        if (!fancy) {
            // TODO: implement for not fancy return
        }

        return String.format(QUERY_TEMPLATE_FANCY, selectVariables, rdfType, predicates, filters);
    }


}
