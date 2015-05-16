package net.lapusiki.app;

import net.lapusiki.config.MainAppConfig;
import net.lapusiki.core.Engine;
import net.lapusiki.core.QueryBuilder;
import net.lapusiki.core.util.QueryHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by kiv1n on 14.05.2015
 */
public class QueryParserWithEngine {

    private static String[] questions = {
            "Кто знает java?",
            "Кто пишет на Java?",
            "Сколько студентов любят футбол?",
            "Где учится студент Вася?",
            "Кто посещает лабу Мобильных разработок?",
            "Кто умеет писать на питоне и смотрел фильм Терминатор?",
            "Сколько языков программирования знает студент Вася?",
            "Какой спорт интересует студента Васю?",
            "Кому нравится книга Гарри Поттер?"
    };


    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainAppConfig.class);

        Engine engine = context.getBean(Engine.class);

        for (String question : questions) {

            System.out.println("Query: " + question);

            QueryHolder queryHolder = engine.processQuery(question);

            String sparqlQuery = new QueryBuilder()
                    .question(queryHolder.getQuestion())
                    .predicateEntityPair(queryHolder.getPredicateEntityPairs())
                    .build();

            System.out.println(sparqlQuery);

        }
    }

}
