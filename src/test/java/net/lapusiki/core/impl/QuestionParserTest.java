package net.lapusiki.core.impl;

import junit.framework.TestCase;
import net.lapusiki.core.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

public class QuestionParserTest extends Assert {

    @Test
    public void testParseForRemovingPrepositions() throws Exception {
        Parser parser = new QuestionParser();
        String[] parsed = (String[]) parser.parse("в на под люблю вас");
        assertEquals(2, parsed.length);
        assertEquals(parsed[0], "люблю");
        assertEquals(parsed[1], "вас");
    }
}