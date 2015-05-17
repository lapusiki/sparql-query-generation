package net.lapusiki.core.service.impl;

import com.opencsv.CSVReader;
import net.lapusiki.core.model.Predicate;
import net.lapusiki.core.model.enums.PredicateType;
import net.lapusiki.core.service.NameService;
import net.lapusiki.core.service.PredicateService;
import net.lapusiki.core.similiarity.StringSimilarityDelegate;
import net.lapusiki.core.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by kiv1n on 18.05.2015
 */
@Component
public class CSVNameServiceImpl implements NameService {

    private final String PREDICATE_FOR_TYPE_NAME = "студент";

    @Autowired
    @Qualifier("stringSimilarity")
    private StringSimilarityDelegate stringSimilarity;
    @Autowired
    private PredicateService predicateService;

    private Map<String, List<String>> names = new HashMap<>();

    @PostConstruct
    public void initEntities() throws IOException {
        // Считываем файл, который содержит имена
        String absolutePath = new File("").getAbsolutePath();
        CSVReader reader = new CSVReader(new FileReader(absolutePath + "/src/main/resources/names.csv"), ',');
        List<String[]> strings = reader.readAll();
        for (String[] line : strings) {
            names.put(line[0], Arrays.asList(Arrays.copyOfRange(line, 1, line.length)));
        }
    }

    @Override
    public String[] normalizePredicatesNearNames(String... words) {

        // Проходим по всем словам и пытаемся найти имя. Если имя найдено,
        // то проверяем предикат перед этим именем. Если предикат не принадлежит к
        // PredicateType.NAME, то добавляем в предложение необходимый предикат.
        for (int i = 0; i < words.length; i++) {
            Pair<String, Integer> bestMatch = stringSimilarity.getBestMatch(names, words[i]);
            if (bestMatch != null) {
                Predicate predicate = predicateService.resolvePredicate(words[i - 1]);
                if (predicate != null && predicate.getPredicateType() != PredicateType.NAME) {
                    return addElementToArray(words, PREDICATE_FOR_TYPE_NAME, i);
                }
            }
        }
        return words;
    }

    // TODO: как-то всё это ужасно выглядит, наверняка можно заменить одной строчкой
    private String[] addElementToArray(String[] array, String element, int index) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(array));
        list.add(index, element);
        return list.toArray(new String[list.size()]);
    }

}
