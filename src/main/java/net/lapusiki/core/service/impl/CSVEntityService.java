package net.lapusiki.core.service.impl;

import com.opencsv.CSVReader;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.service.EntityService;
import net.lapusiki.core.similiarity.StringSimilarityDelegate;
import net.lapusiki.core.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kiv1n on 16.05.2015
 */
@Component
public class CSVEntityService implements EntityService {

    @Autowired
    @Qualifier("stringSimilarity")
    private StringSimilarityDelegate stringSimilarity;

    private Map<String, List<String>> entities = new HashMap<>();

    @PostConstruct
    public void initEntities() throws IOException {

        String absolutePath = new File("").getAbsolutePath();
        List<String[]> strings;
        CSVReader reader;

        // Считываем файл, который содержит entities
        reader = new CSVReader(new FileReader(absolutePath + "/src/main/resources/entity_url_label.csv"), ',');
        strings = reader.readAll();
        for (String[] line : strings) {
            entities.put(line[0], Arrays.asList(Arrays.copyOfRange(line, 1, line.length)));
        }
        // Считываем файл, который содержит имена
        reader = new CSVReader(new FileReader(absolutePath + "/src/main/resources/names.csv"), ',');
        strings = reader.readAll();
        for (String[] line : strings) {
            entities.put(line[0], Arrays.asList(Arrays.copyOfRange(line, 1, line.length)));
        }
    }

    @Override
    public Entity resolveEntity(String... words) {
        Pair<String, Integer> bestMatch = stringSimilarity.getBestMatch(entities, words);
        return (bestMatch != null ? new Entity(bestMatch.getFirst()) : null);
    }

}
