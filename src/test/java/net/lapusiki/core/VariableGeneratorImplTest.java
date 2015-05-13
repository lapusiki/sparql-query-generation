package net.lapusiki.core;

import net.lapusiki.core.impl.PersonVariableGenerator;

import static org.junit.Assert.*;

/**
 */
public class VariableGeneratorImplTest {

    @org.junit.Test
    public void testGetVariable() throws Exception {
        VariableGenerator variableGenerator = new PersonVariableGenerator();
        assertEquals("?Person1_name", variableGenerator.getVariable("foaf:name"));
    }
}