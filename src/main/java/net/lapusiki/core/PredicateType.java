package net.lapusiki.core;

/**
 * Created by kiv1n on 15.05.2015
 */
public enum PredicateType {

    NAME("foaf:name"),
    INTEREST("foaf:interest"),
    ITIS_LAB(":itisLab"),
    PL("dbpedia-owl:programmingLanguage");

    private final String value;

    PredicateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
