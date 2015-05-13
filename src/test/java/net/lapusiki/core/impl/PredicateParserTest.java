package net.lapusiki.core.impl;

import net.lapusiki.core.model.Holder;
import net.lapusiki.core.model.Predicate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class PredicateParserTest {

    @Test
    public void testParse() throws Exception {

        PredicateParser predicateParser = new PredicateParser();
        Holder<Predicate, String> parse = predicateParser.parse("любят футбол");
        System.out.println(parse);

    }
}