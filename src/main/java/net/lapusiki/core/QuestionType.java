package net.lapusiki.core;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
public enum QuestionType {

    WHO_QUESTION("select ?fullname"),
    COUNT_QUESTION("select count(varname)");

    private final String description;

    QuestionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
