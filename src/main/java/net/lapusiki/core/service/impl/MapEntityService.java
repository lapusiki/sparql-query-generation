package net.lapusiki.core.service.impl;

import com.google.common.collect.Lists;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.service.EntityService;
import net.lapusiki.core.similiarity.StringSimilarityDelegate;
import net.lapusiki.core.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kiv1n on 16.05.2015
 */
@Component
public class MapEntityService implements EntityService {

    @Autowired
    @Qualifier("stringSimilarity")
    private StringSimilarityDelegate stringSimilarity;

    private Map<String, List<String>> entities = new HashMap<String, List<String>>() {{
        this.put("http://dbpedia.org/page/Java_(programming_language)", Lists.asList("java", "ява, джава".split(", ")));
        this.put("http://dbpedia.org/resource/Football", Lists.asList("футбол", "football, футбольчик".split(", ")));
        this.put("Android", Lists.asList("мобильные разработки", "андроид, android".split(", ")));
        this.put("http://dbpedia.org/resource/Python_(programming_language)", Lists.asList("python", "питон, змей".split(", ")));
    }};

    @Override
    public Entity resolveEntity(String... words) {
        Pair<String, Integer> bestMatch = stringSimilarity.getBestMatch(entities, words);
        return (bestMatch != null ? new Entity(bestMatch.getFirst()) : null);
    }

}
