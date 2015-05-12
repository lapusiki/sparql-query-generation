package net.lapusiki.core;

import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Predicate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class QueryBuilderTest {

    @Test
    public void testBuild() throws Exception {
        System.out.println(new QueryBuilder().
                type("person").
                predicate(new Predicate("name", "interest")).
                entity(new Entity("petr", "java"))
                .fancy(true)
                .build());
    }
}