package net.lapusiki.app;

import net.lapusiki.core.Engine;
import net.lapusiki.core.QueryBuilder;
import net.lapusiki.core.impl.EngineV1;
import net.lapusiki.core.model.QueryHolder;

/**
 * Created by kiv1n on 14.05.2015
 */
public class QueryParserWithEngine {

    private static Engine engine = new EngineV1();

    // На данный момент правильно обрабатываются запросы: 1, 3, 4
    private static String[] questions = {
            "Кто знает java?",
            "Сколько студентов любят футбол?",
            "Где учится Вася?",
            "Кто посещает лабу Мобильных разработок?",
            "Кто умеет писать на питоне и смотрел фильм Терминатор?",
            "Сколько языков программирования знает Вася?"
    };


    public static void main(String[] args) throws Exception {

        String query = questions[1];

        System.out.println("Query: " + query);

        QueryHolder queryHolder = engine.processQuery(query);

        String sparqlQuery = new QueryBuilder()
                .rdfType("Person")
                .question(queryHolder.getQuestion())
                .predicateEntityPair(queryHolder.getPredicateEntityPairs())
                .build();

        System.out.println(sparqlQuery);

    }

}
