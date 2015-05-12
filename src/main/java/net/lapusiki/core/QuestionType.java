package net.lapusiki.core;

/**
 * Created by blvp on 12.05.15.
 */
public enum QuestionType {
    SIMPLE_QUESTION("simple select ?varname query"),
    CUSTOM_QUESTION("count(?varname) query");
    QuestionType(String description) {
        this.description = description;
    }

    private final String description;

    public String getDescription() {
        return description;
    }
}
