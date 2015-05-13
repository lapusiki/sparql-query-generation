package net.lapusiki.core.parser.impl;

import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Pair;

/**
 * Created by kiv1n on 12.05.15.
 */
public class EntityParser implements Parser {

    @Override
    public Pair<Entity, String> parse(String sentence) throws Exception {

        // TODO: реализовать использованиее стоп слов

        // TODO: реализовать разделение на остаточную часть после объекта

        return new Pair<>(new Entity(sentence), null);
    }

}
