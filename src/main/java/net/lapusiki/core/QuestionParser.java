package net.lapusiki.core;

import java.util.regex.Pattern;

/**
 * Created by blvp on 12.05.15.
 */
public class QuestionParser implements Parser{

    @Override
    public String[] parse(String sentence) {
        sentence = sentence.replaceAll("[?,.!'\"/*-+]", "");
        return sentence.split(" ");
    }
}
