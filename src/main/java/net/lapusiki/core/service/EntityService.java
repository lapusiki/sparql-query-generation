package net.lapusiki.core.service;

import net.lapusiki.core.model.Entity;

/**
 * Created by kiv1n on 16.05.2015
 */
public interface EntityService {
    Entity resolveEntity(String... words);
}
