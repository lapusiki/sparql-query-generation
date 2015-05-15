package net.lapusiki.core;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
public enum QuestionType {
    WHO_QUESTION("select ?fullname"),
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
