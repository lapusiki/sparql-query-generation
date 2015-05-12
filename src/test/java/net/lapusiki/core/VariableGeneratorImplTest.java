package net.lapusiki.core;

import static org.junit.Assert.*;

/**
 */
public class VariableGeneratorImplTest {

    @org.junit.Test
    public void testGetVariable() throws Exception {
        VariableGenerator variableGenerator = new VariableGeneratorImpl();
        assertEquals("?Person1_name", variableGenerator.getVariable("foaf:name"));
    }
}