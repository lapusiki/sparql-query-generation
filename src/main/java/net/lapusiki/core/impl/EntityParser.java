package net.lapusiki.core.impl;

import net.lapusiki.core.Parser;
import net.lapusiki.core.model.Entity;
import net.lapusiki.core.model.Holder;

/**
 */
public class EntityParser implements Parser {

    @Override
    public Holder<Entity, String> parse(String sentence) throws Exception {

        // TODO: implement with stop-words

        return new Holder<>(new Entity(sentence), null);
    }

}
