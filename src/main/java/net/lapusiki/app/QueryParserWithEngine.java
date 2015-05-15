package net.lapusiki.app;

import net.lapusiki.core.Engine;
import net.lapusiki.core.impl.EngineV1;
import net.lapusiki.core.model.QueryHolder;

/**
 * Created by kiv1n on 14.05.2015
 */
public class QueryParserWithEngine {

    private static Engine engine = new EngineV1();

    public static void main(String[] args) throws Exception {

        String query = "Кто умеет писать на питоне и смотрел фильм Терминатор?";

        System.out.println("Query: " + query);

        QueryHolder queryHolder = engine.processQuery(query);

        System.out.println(queryHolder);

    }

}
