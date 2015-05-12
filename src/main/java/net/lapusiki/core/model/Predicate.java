package net.lapusiki.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Predicate {

    private List<String> values = new ArrayList<>();

    public Predicate(String... values) {
        for (String value : values) {
            this.values.add(value);
        }
    }

    public Predicate(List<String> values) {
        this.values = values;
    }

    public Predicate() {
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate that = (Predicate) o;

        return !(values != null ? !values.equals(that.values) : that.values != null);

    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Predicate{" +
                "values=" + values +
                '}';
    }
}
