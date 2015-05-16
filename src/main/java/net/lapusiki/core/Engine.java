package net.lapusiki.core;

import net.lapusiki.core.util.QueryHolder;

/**
 * Created by kiv1n on 13.05.2015
 */
public interface Engine {

    QueryHolder processQuery(String sentence) throws Exception;

}
