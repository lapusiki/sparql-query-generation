package net.lapusiki.app;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by kiv1n on 16.05.2015
 */
public class CSVReaderExample {

    public static void main(String[] args) throws IOException {

        String absolutePath = new File("").getAbsolutePath();

        CSVReader reader = new CSVReader(new FileReader(absolutePath + "/src/main/resources/entity_url_label.csv"), ';');
        List<String[]> strings = reader.readAll();

        System.out.println(strings.size());


    }

}
