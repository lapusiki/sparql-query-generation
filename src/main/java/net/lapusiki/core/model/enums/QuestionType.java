package net.lapusiki.core.model.enums;

/**
 * Created by blvp on 12.05.15.
 *
 * @author blvp
 * @author kiv1n
 */
public enum QuestionType {

    GET_NAME_QUESTION("select ?fullname"),
    GET_VAR_QUESTION("select ?var"),
    GET_COUNT_QUESTION("select count(?var)");

    private final String description;

    QuestionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
