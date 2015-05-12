package net.lapusiki.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PredicateWrapper {

    private List<String> values = new ArrayList<>();

    public PredicateWrapper(String value) {
        values.add(value);
    }

    public PredicateWrapper(List<String> values) {
        this.values = values;
    }

    public PredicateWrapper() {
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

        PredicateWrapper that = (PredicateWrapper) o;

        return !(values != null ? !values.equals(that.values) : that.values != null);

    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PredicateWrapper{" +
                "values=" + values +
                '}';
    }
}
