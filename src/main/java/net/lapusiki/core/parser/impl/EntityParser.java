package net.lapusiki.core.parser.impl;

import net.lapusiki.core.parser.Parser;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Holder;

/**
 */
public class EntityParser implements Parser {

    @Override
    public Holder<Entity, String> parse(String sentence) throws Exception {

        // TODO: реализовать использованиее стоп слов

        // TODO: реализовать разделение на остаточную часть после объекта

        return new Holder<>(new Entity(sentence), null);
    }

}
