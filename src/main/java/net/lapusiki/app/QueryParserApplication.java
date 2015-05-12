package net.lapusiki.app;

import net.lapusiki.core.*;
import net.lapusiki.core.impl.MapPredicateService;
import net.lapusiki.core.impl.MapQuestionService;
import net.lapusiki.core.impl.QuestionParser;
import net.lapusiki.core.impl.VariableGeneratorImpl;

/**
 * Created by blvp on 12.05.15.
 */
public class QueryParserApplication {

    private static Parser parser = new QuestionParser();
    private static VariableGenerator variableGenerator = new VariableGeneratorImpl();
    private static PredicateService predicateService = new MapPredicateService();
    private static QuestionService questionService = new MapQuestionService();



    public static void main(String[] args) {
        String[] parsedItems = (String[]) parser.parse("Кто знает java?");
        int length = parsedItems.length;

        if(length != 3) throw new UnsupportedOperationException("Пока не умеем распознавать такие запросы!");

        String question = parsedItems[0];
        String predicate = predicateService.resolvePredicate(parsedItems[1]);
        String entity = parsedItems[2];
        QuestionType questionType = questionService.resolveQuestion(question);
        String variableName = variableGenerator.getVariable(predicate);



        String query = "SELECT "+questionService.getSelectOption(questionType, variableName) + "\n" +
                "WHERE { \n ?person rdf:type foaf:Person \n" +
                " ?person "+ predicate + " " + variableName + "\n" +
                "FILTER (" + variableName + " = \"" + entity + "\")\n" +
                "}";
        System.out.println(query);
    }
}
