package net.lapusiki.core.model;

/**
 */
public class Question {

    /**
     * Variable - SELECT ?variable
     * Count - SELECT count(?variable)
     */
    public enum Types {
        VARIABLE, COUNT
    }

    private Types type;

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "type=" + type +
                '}';
    }
}
