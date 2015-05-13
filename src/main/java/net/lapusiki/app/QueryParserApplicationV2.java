package net.lapusiki.app;

import net.lapusiki.core.QueryBuilder;
import net.lapusiki.core.impl.EntityParser;
import net.lapusiki.core.impl.PredicateParser;
import net.lapusiki.core.impl.QuestionParser;
import net.lapusiki.core.model.*;

/**
 * Created by blvp on 12.05.15.
 */
public class QueryParserApplicationV2 {

    private static final String TYPE_FOR_SPARQL = "person";

    private static QuestionParser questionParser = new QuestionParser();
    private static PredicateParser predicateParser = new PredicateParser();
    private static EntityParser entityParser = new EntityParser();

    public static void main(String[] args) throws Exception {

        String inputQuery = "Где учится Вася?";

        Holder<Question, String> questionHolder = questionParser.parse(inputQuery);
        Holder<Predicate, String> predicateHolder = predicateParser.parse(questionHolder.getObject2());
        Holder<Entity, String> entityHolder = entityParser.parse(predicateHolder.getObject2());

        GlobalQueryHolder globalQueryHolder = new GlobalQueryHolder();
        globalQueryHolder.getQuestions().add(questionHolder.getObject1());
        globalQueryHolder.getPredicates().add(predicateHolder.getObject1());
        globalQueryHolder.getEntities().add(entityHolder.getObject1());

        String query = new QueryBuilder()
                .type(TYPE_FOR_SPARQL)
                .questions(globalQueryHolder.getQuestions())
                .predicates(globalQueryHolder.getPredicates())
                .entities(globalQueryHolder.getEntities())
                .build();

        System.out.println(query);
    }
}
