package net.lapusiki.core;

import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.model.Question;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 */
public class QueryBuilderTest {

    @Test
    public void testBuild() throws Exception {

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(QuestionType.SIMPLE_QUESTION));
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(new Predicate("foaf:interest"));
        List<Entity> entityList = new ArrayList<>();
        entityList.add(new Entity("java"));

        System.out.println(new QueryBuilder()
                .type("person")
                .questions(questions)
                .predicates(predicateList)
                .entities(entityList)
                .build());
    }
}