package net.lapusiki.core.model;

/**
 */
public class Predicate {

    private String value;

    public Predicate() {
    }

    public Predicate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate predicate = (Predicate) o;

        return !(value != null ? !value.equals(predicate.value) : predicate.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Predicate{" +
                "value='" + value + '\'' +
                '}';
    }
}
