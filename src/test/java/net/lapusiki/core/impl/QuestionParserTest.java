package net.lapusiki.core.impl;

import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.model.Holder;
import net.lapusiki.core.model.Question;
import net.lapusiki.core.parser.impl.QuestionParser;
import org.junit.Assert;
import org.junit.Test;

public class QuestionParserTest extends Assert {

    @Test
    public void testParseForRemovingPrepositions() throws Exception {
        Parser parser = new QuestionParser();
        String[] parsed = (String[]) parser.parse("в на под люблю вас");
        assertEquals(2, parsed.length);
        assertEquals(parsed[0], "люблю");
        assertEquals(parsed[1], "вас");
    }


    @Test
    public void testSplitQueryToQuestionAndRestPart() throws Exception {

        QuestionParser parser = new QuestionParser();

        String question = "Сколько студентов любят футбол?";

        Holder<Question, String> holder = parser.parse(question);

        System.out.println(holder);

    }
}