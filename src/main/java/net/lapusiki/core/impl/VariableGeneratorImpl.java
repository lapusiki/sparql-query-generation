package net.lapusiki.core.impl;

import net.lapusiki.core.VariableGenerator;

/**
 */
public class VariableGeneratorImpl implements VariableGenerator {

    private static final String OBJECT = "?Person1_";

    @Override
    public String getVariable(String raw) {
        String[] splitRaw = raw.split(":");
        return OBJECT + splitRaw[1];
    }

}
