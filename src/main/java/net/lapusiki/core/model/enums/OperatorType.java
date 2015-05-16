package net.lapusiki.core.model.enums;

/**
 * Created by kiv1n on 15.05.2015
 */
public enum OperatorType {

    AND("и"), OR("или");

    private final String description;

    OperatorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
