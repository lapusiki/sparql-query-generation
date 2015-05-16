package net.lapusiki.app;

import net.lapusiki.config.MainAppConfig;
import net.lapusiki.core.Engine;
import net.lapusiki.core.QueryBuilder;
import net.lapusiki.core.impl.EngineV1;
import net.lapusiki.core.model.QueryHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by kiv1n on 14.05.2015
 */
public class QueryParserWithEngine {

    private static String[] questions = {
            "Кто знает java?",
            "Сколько студентов любят футбол?",
            "Где учится студент Вася?",
            "Кто посещает лабу Мобильных разработок?",
            "Кто умеет писать на питоне и смотрел фильм Терминатор?",
            "Сколько языков программирования знает студент Вася?"
    };


    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainAppConfig.class);

        Engine engine = context.getBean(Engine.class);

        String query = questions[5];

        System.out.println("Query: " + query);

        QueryHolder queryHolder = engine.processQuery(query);

        String sparqlQuery = new QueryBuilder()
                .question(queryHolder.getQuestion())
                .predicateEntityPair(queryHolder.getPredicateEntityPairs())
                .build();

        System.out.println(sparqlQuery);

    }

}
