package net.lapusiki.core.impl;

import net.lapusiki.core.VariableGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class PersonVariableGenerator implements VariableGenerator {

    private static final String OBJECT = "?Person1_";

    @Override
    public String getVariable(String raw) {
        String[] splitRaw = raw.split(":");
        return OBJECT + splitRaw[1];
    }

}
