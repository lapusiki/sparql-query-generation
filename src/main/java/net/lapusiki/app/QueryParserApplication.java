package net.lapusiki.app;

import com.google.common.collect.Lists;
import net.lapusiki.core.*;
import net.lapusiki.core.impl.*;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.model.Question;
import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.parser.impl.PrepositionsParser;

import java.util.List;

/**
 * Created by blvp on 12.05.15.
 */
public class QueryParserApplication {

    private static Parser parser = new PrepositionsParser();
    private static PredicateService predicateService = new MapPredicateService();
    private static QuestionService questionService = new MapQuestionService();



    public static void main(String[] args) throws Exception {
        String[] parsedItems = (String[]) parser.parse("Кто знает java?");
        int length = parsedItems.length;

        if(length != 3) throw new UnsupportedOperationException("Пока не умеем распознавать такие запросы!");

        String question = parsedItems[0];
        Predicate predicate = predicateService.resolvePredicate(parsedItems[1]);
        Entity entity = new Entity(parsedItems[2]);
        QuestionType questionType = questionService.resolveQuestion(question);

        List<Question> questionList = Lists.newArrayList(new Question(questionType));
        List<Predicate> predicateList = Lists.newArrayList(predicate);
        List<Entity> entityList = Lists.newArrayList(entity);
        String query = new QueryBuilder()
                .type("person")
                .predicates(predicateList)
                .entities(entityList)
                .questions(questionList)
                .build();

        System.out.println(query);
    }
}
