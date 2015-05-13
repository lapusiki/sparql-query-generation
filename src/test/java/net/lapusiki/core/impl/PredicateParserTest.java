package net.lapusiki.core.impl;

import net.lapusiki.core.model.Pair;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.parser.impl.PredicateParser;
import org.junit.Test;

/**
 */
public class PredicateParserTest {

    @Test
    public void testParse() throws Exception {

        PredicateParser predicateParser = new PredicateParser();
        Pair<Predicate, String> parse = predicateParser.parse("любят футбол");
        System.out.println(parse);

    }
}