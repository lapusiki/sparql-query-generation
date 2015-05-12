package net.lapusiki.app;

import net.lapusiki.core.Parser;
import net.lapusiki.core.QuestionParser;

/**
 * Created by blvp on 12.05.15.
 */
public class QueryParserApplication {

    private static Parser parser = new QuestionParser();

    public static void main(String[] args) {
        String[] parseItems = (String[]) parser.parse("Кто знает java?");
        int length = parseItems.length;

        if(length != 3) throw new UnsupportedOperationException("Пока не умеем распознавать такие запросы!");

        for (String parseItem : parseItems) {
            System.out.println(parseItem);
        }



    }
}
