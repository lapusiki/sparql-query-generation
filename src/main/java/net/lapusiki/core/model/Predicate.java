package net.lapusiki.core.model;

/**
 */
public class Predicate {

    private String value;

    private OperatorType afterPredicateOperator;

    public Predicate() {
    }

    public Predicate(String value) {
        this.value = value;
    }

    public Predicate(String value, OperatorType afterPredicateOperator) {
        this.value = value;
        this.afterPredicateOperator = afterPredicateOperator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OperatorType getAfterPredicateOperator() {
        return afterPredicateOperator;
    }

    public void setAfterPredicateOperator(OperatorType afterPredicateOperator) {
        this.afterPredicateOperator = afterPredicateOperator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate predicate = (Predicate) o;

        if (value != null ? !value.equals(predicate.value) : predicate.value != null) return false;
        return afterPredicateOperator == predicate.afterPredicateOperator;

    }

    @Override
    public int hashCode() {
        // Для хэшкода берутся только положительные числа, чтобы
        // они подходили для sparql запроса
        // Лучше не трогать, итак должно работать
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (afterPredicateOperator != null ? afterPredicateOperator.hashCode() : 0);
        return Math.abs(result);
    }

    @Override
    public String toString() {
        return "Predicate{" +
                "value='" + value + '\'' +
                ", afterPredicateOperator=" + afterPredicateOperator +
                '}';
    }
}
