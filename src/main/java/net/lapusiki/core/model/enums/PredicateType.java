package net.lapusiki.core.model.enums;

/**
 * Created by kiv1n on 15.05.2015
 */
public enum PredicateType {

    NAME("foaf:name"),
    INTEREST("foaf:interest"),
    ITIS_LAB(":itisLab"),
    PL("dbpedia-owl:programmingLanguage"),
    MBOX("foaf:mbox <?>");

    private final String value;

    PredicateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PredicateType fromString(String key) {
        for (PredicateType predicateType : PredicateType.values()) {
            if(predicateType.getValue().equals(key)) return predicateType;
        }
        return null;
    }
}
