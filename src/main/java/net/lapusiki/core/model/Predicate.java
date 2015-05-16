package net.lapusiki.core.model;

import net.lapusiki.core.PredicateType;

/**
 */
public class Predicate {

    private PredicateType predicateType;

    private Integer predicateLength;

    private OperatorType afterPredicateOperator;

    public Predicate() {
    }

    public Predicate(PredicateType predicateType){
        this(predicateType, 0);
    }
    public Predicate(PredicateType predicateType, Integer predicateLength) {
        this.predicateType = predicateType;
        this.predicateLength = predicateLength;
    }

    public Predicate(PredicateType predicateType, OperatorType afterPredicateOperator) {
        this.predicateType = predicateType;
        this.afterPredicateOperator = afterPredicateOperator;
    }

    public PredicateType getPredicateType() {
        return predicateType;
    }

    public void setPredicateType(PredicateType predicateType) {
        this.predicateType = predicateType;
    }

    public OperatorType getAfterPredicateOperator() {
        return afterPredicateOperator;
    }

    public void setAfterPredicateOperator(OperatorType afterPredicateOperator) {
        this.afterPredicateOperator = afterPredicateOperator;
    }

    public Integer getPredicateLength() {
        return predicateLength;
    }

    public void setPredicateLength(Integer predicateLength) {
        this.predicateLength = predicateLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate predicate = (Predicate) o;

        if (predicateType != predicate.predicateType) return false;
        return afterPredicateOperator == predicate.afterPredicateOperator;

    }

    @Override
    public int hashCode() {
        int result = predicateType != null ? predicateType.hashCode() : 0;
        result = 31 * result + (afterPredicateOperator != null ? afterPredicateOperator.hashCode() : 0);

        // Для хэшкода берутся только положительные числа, чтобы
        // они подходили для sparql запроса
        // Лучше не трогать, итак должно работать
        return Math.abs(result);
    }

    @Override
    public String toString() {
        return "Predicate{" +
                "predicateType=" + predicateType +
                ", afterPredicateOperator=" + afterPredicateOperator +
                '}';
    }
}
